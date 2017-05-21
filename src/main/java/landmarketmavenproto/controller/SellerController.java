package landmarketmavenproto.controller;

import landmarketmavenproto.model.Seller;
import landmarketmavenproto.repository.SellerRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik_NB on 18.03.2017.
 */
@RestController
@RequestMapping("/seller")
//@CrossOrigin
public class SellerController {
    @Autowired
    SellerRepository srepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSeller(@RequestBody Map<String, Object> sellerMap) {

        String login = sellerMap.get("login").toString();
        String email = sellerMap.get("email").toString();
        String password = sellerMap.get("password").toString();
        String confirm = sellerMap.get("confirm").toString();


//        if((srepository.findByLogin(login)) != null) {
//            JSONObject response = new JSONObject();
//            response.put("message", "Login already exists");
//            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//        }

        return new ResponseEntity<>(srepository.save(new Seller(login,email, password, confirm)), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "{sellerId}")
    public ResponseEntity<?> getSellerLoginAndPassword(@PathVariable(value = "sellerId") String sellerId){

        Seller seller = srepository.findOne(sellerId);
        String login = seller.getLogin();
        String password = seller.getPassword();

        JSONObject response = new JSONObject();

        response.put("message", "seller login and password");
        response.put("seller's login", login);
        response.put("seller's password", password);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{sellerId}")
    public ResponseEntity<?> editSeller(@PathVariable(value = "sellerId") String sellerId, @RequestBody Map<String, Object> sellerMap) {

        Seller seller = new  Seller(sellerMap.get("login").toString(),
                             sellerMap.get("email").toString(),
                             sellerMap.get("password").toString());
        seller.setId(sellerId);

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
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllSellers() {

        List<Seller> sellers = srepository.findAll();

        JSONObject response = new JSONObject();
        response.put("totalSellers", sellers.size());
        response.put("sellers", sellers);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
