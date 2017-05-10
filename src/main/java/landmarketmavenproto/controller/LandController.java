package landmarketmavenproto.controller;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import landmarketmavenproto.model.Land;
import landmarketmavenproto.repository.LandRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/land")
public class LandController {
    @Autowired
    LandRepository lrepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createLand(@RequestBody Map<String, Object> landMap){
//        Land land = new Land(landMap.get("area").toString(),
//                landMap.get("assingment").toString());
//        lrepository.save(land);
//
//        Map<String, Object> response = new LinkedHashMap<String, Object>();
//        response.put("message", "land created successfully");
//        response.put("land", land);
//        return response;
        String area = landMap.get("area").toString();
        String assignment = landMap.get("assignment").toString();
        String price = landMap.get("price").toString();
        String description = landMap.get("description").toString();
        String address = landMap.get("address").toString();
        String owner = landMap.get("owner").toString();
        //JSONObject response = new JSONObject();
        //response.put("message", "land created successfully");
        return new ResponseEntity<>(lrepository.save(new Land(area, assignment, price,
                description, address, owner )), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{landId}")
    public ResponseEntity<?> getLandDetails(@PathVariable("landId") String landId) {
        //JSONObject response = new JSONObject();
        return new ResponseEntity<>(lrepository.findOne(landId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{landId}")
    public ResponseEntity<?> editLand(@PathVariable("landId") String landId, @RequestBody Map<String, Object> landMap){
        Land land = new Land(landMap.get("area").toString(),
                landMap.get("assignment").toString(),  landMap.get("price").toString(),
                landMap.get("description").toString(), landMap.get("address").toString(),
                landMap.get("owner").toString());
        land.setId(landId);

//        Map<String, Object> response = new LinkedHashMap<String, Object>();
//        response.put("message", "land updates successfully");
//        response.put("land", lrepository.save(land));
//        return response;
        JSONObject response = new JSONObject();
        response.put("message", "land updated successfully");
        response.put("land", lrepository.save(land));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{landId}")
        public ResponseEntity<?> deleteLand(@PathVariable("landId") String landId){
        lrepository.delete(landId);
//        Map<String, Object> response = new HashMap<String, Object>();
//        response.put("message", "land deleted successfully");
//        return response;
        JSONObject response = new JSONObject();
        response.put("message", "land deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
         public ResponseEntity<?> getAllLand(){
         List<Land> lands = lrepository.findAll();
//         Map<String, Object> response = new LinkedHashMap<String, Object>();
//         response.put("totalLands", lands.size());
//         response.put("lands", lands);
//         return response;
        JSONObject response = new JSONObject();
        response.put("totalLands", lands.size());
        response.put("lands", lands);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//    public byte[] imagesBytes {
//        GridFS fs= new GridFS(land, "photo");
//        GridFSInputFile in =fs.createFile(imagesBytes);
//        in.save();
//
//    }

}
