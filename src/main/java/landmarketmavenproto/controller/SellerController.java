package landmarketmavenproto.controller;

import landmarketmavenproto.model.Seller;
import landmarketmavenproto.repository.SellerRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Nik_NB on 18.03.2017.
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerRepository srepository;

    private ArrayList<String> logins = new ArrayList<String>();
    private ArrayList<String> passwords = new ArrayList<String>();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSeller(@RequestBody Map<String, Object> sellerMap) {
//    Seller seller = new Seller(sellerMap.get("passport").toString(),
//            sellerMap.get("companyName").toString(),
//            sellerMap.get("phone").toString(),
//            sellerMap.get("address").toString(),
//            sellerMap.get("email").toString(),
//            sellerMap.get("managerName").toString(),
//            sellerMap.get("skype").toString(),
//            sellerMap.get("login").toString(),
//            sellerMap.get("password").toString()
//            );


        String login = sellerMap.get("login").toString();

        if((srepository.findByLogin(login)) != null) {
            JSONObject response = new JSONObject();
            response.put("message", "Login already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        String password = sellerMap.get("password").toString();

        return new ResponseEntity<>(srepository.save(new Seller(login, password)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sellerId}")
    public ResponseEntity<?> getSellerDetails(@PathVariable(value = "sellerId") String sellerId) {
        JSONObject response = new JSONObject();
        response.put("message", "seller details");
        response.put("seller", srepository.findOne(sellerId));
        return new ResponseEntity<>(response, HttpStatus.OK);
        //return srepository.findOne(sellerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{sellerId}")
    public ResponseEntity<?> editSeller(@PathVariable(value = "sellerId") String sellerId, @RequestBody Map<String, Object> sellerMap) {
//            Seller seller = new Seller(sellerMap.get("passport").toString(),
//                    sellerMap.get("companyName").toString(),
//                    sellerMap.get("phone").toString(),
//                    sellerMap.get("address").toString(),
//                    sellerMap.get("email").toString(),
//                    sellerMap.get("managerName").toString(),
//                    sellerMap.get("skype").toString(),
//                    sellerMap.get("login").toString(),
//                    sellerMap.get("password").toString()
//            );
        Seller seller = new Seller(
                sellerMap.get("login").toString(),
                sellerMap.get("password").toString()
        );
        seller.setId(sellerId);
//
//        Map<String, Object> response = new LinkedHashMap<String, Object>();
//        response.put("message", "Seller updated successfully");
//        response.put("seller", srepository.save(seller));
//        return response;

        JSONObject response = new JSONObject();
        response.put("message", "Seller updated successfully");
        response.put("seller", srepository.save(seller));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{sellerId}")
    public ResponseEntity<?> deleteSeller(@PathVariable(value = "sellerId") String sellerId) {
        srepository.delete(sellerId);
        JSONObject response = new JSONObject();
        response.put("message", "seller deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
//        Map<String, Object> response = new HashMap<String, Object>();
//        response.put("message", "Seller deleted successfully");
//        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllSellers() {
        List<Seller> sellers = srepository.findAll();
        JSONObject response = new JSONObject();
        response.put("totalSellers", sellers.size());
        response.put("sellers", sellers);
        return new ResponseEntity<>(response, HttpStatus.OK);
//        Map<String, Object> response = new LinkedHashMap<String, Object>();
//        response.put("totalSellers", sellers.size());
//        response.put("sellers", sellers);
//        return response;
    }


}
