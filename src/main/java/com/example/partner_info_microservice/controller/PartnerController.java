package com.example.partner_info_microservice.controller;

import com.example.partner_info_microservice.model.Game;
import com.example.partner_info_microservice.model.Partner;
import com.example.partner_info_microservice.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private static final Logger log = LoggerFactory.getLogger(PartnerController.class);
    @Value("${player.service.url}")
    private String playerServiceEndPoint;

    @Autowired
    private RestTemplate restTemplate;

    private static List<Partner> partnerList = new ArrayList<>();

    static{
        partnerList.add(new Partner("p1",
                "partner-1",
                List.of(new Game("g1", "Game 1"),new Game("g2", "Game 2")), true));
        partnerList.add(new Partner("p2",
                "partner-2",
                List.of(new Game("g2", "Game 2"),new Game("g3", "Game 3")), true));
    }

    @GetMapping
    public ResponseEntity<List<Partner>> getAllPartners(){
        return ResponseEntity.ok(partnerList);
    }

    @PostMapping
    public ResponseEntity<String> addPartner(@RequestBody Partner partner){
        partnerList.add(partner);
        return ResponseEntity.ok("partner has been added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePartner(@PathVariable("id") String partnerKey, @RequestBody Partner partner){

        Partner partnerTobeUpdated=partnerList.stream()
                .filter(partner1 -> partner1.getPartnerKey().equalsIgnoreCase(partnerKey)).findFirst().map(p->{
                    p.setPartnerName(partner.getPartnerName());
                    p.setGames(partner.getGames());
                    p.setEnabled(partner.isEnabled());
                    return p;
                }).orElse(null);

        if(partnerTobeUpdated==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Partner not found");
        }
        return ResponseEntity.ok("partner has been updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartnerById(@PathVariable("id") String id){

        List<Partner> partners=partnerList.stream().filter(partner -> partner.getPartnerKey().equalsIgnoreCase(id)).toList();
        if(partners.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Partner Found");
        }
        Partner partner=partners.get(0);
        try{
            String url=playerServiceEndPoint+"/partner/"+partner.getPartnerKey();
            log.debug("url is {}", url);
            ResponseEntity<List<Player>> playerResponseEntity=restTemplate.exchange(url,
                    HttpMethod.GET,null, new ParameterizedTypeReference<List<Player>>() {});
            if(playerResponseEntity.getStatusCode()==HttpStatus.OK){
                List<Player> players=playerResponseEntity.getBody();
                partner.setPlayers(players);
            }
            return ResponseEntity.ok(partner);
        }catch (Exception e){
            log.error("Exception occurred",e);
            if(e instanceof HttpServerErrorException){
                HttpServerErrorException serverErrorException=(HttpServerErrorException) e;
                return ResponseEntity
                        .status(serverErrorException.getStatusCode())
                        .body(serverErrorException.getResponseBodyAsString());
            }

        }
        return null;
    }
}
