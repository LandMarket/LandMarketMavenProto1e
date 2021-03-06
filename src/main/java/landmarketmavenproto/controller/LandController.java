package landmarketmavenproto.controller;

import landmarketmavenproto.model.Land;
import landmarketmavenproto.repository.LandRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik_NB on 19.03.2017.
 */
@RestController
@RequestMapping("/land")
public class LandController {
    @Autowired
    LandRepository lrepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createLand(@RequestBody Map<String, Object> landMap){


        String area = landMap.get("area").toString();
        String assignment = landMap.get("assignment").toString();
        String price = landMap.get("price").toString();
        String description = landMap.get("description").toString();
        String address = landMap.get("address").toString();
        String owner = landMap.get("owner").toString();


        return new ResponseEntity<>(lrepository.save(new Land(area, assignment, price, description, address, owner)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{landId}")
    public ResponseEntity<?> getLandDetails(@PathVariable("landId") String landId) {

        JSONObject response = new JSONObject();
        response.put("message", "land details");
        response.put("land", lrepository.findOne(landId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{landId}")
    public ResponseEntity<?> editLand(@PathVariable("landId") String landId, @RequestBody Map<String, Object> landMap){

        Land land = new Land(landMap.get("area").toString(),
                landMap.get("assignment").toString(),
                landMap.get("price").toString(),
                landMap.get("description").toString(),
                landMap.get("address").toString(),
                landMap.get("owner").toString());
        land.setId(landId);

        JSONObject response = new JSONObject();
        response.put("message", "land updated successfully");
        response.put("land", lrepository.save(land));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{landId}")
    public ResponseEntity<?> deleteLand(@PathVariable("landId") String landId){

        lrepository.delete(landId);

        JSONObject response = new JSONObject();
        response.put("message", "land deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)

    public ResponseEntity<?> getAllLand(){

        List<Land> lands = lrepository.findAll();

        JSONObject response = new JSONObject();
        response.put("totalLands", lands.size());
        response.put("lands", lands);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
