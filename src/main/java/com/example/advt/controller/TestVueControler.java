package com.example.advt.controller;

import com.example.advt.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *14.09.2019
 */
@RestController
@RequestMapping("testVue")
public class TestVueControler {
    @Value("${animals.path}")
    private String animalsPath;
    private Integer counter = 0;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(); //{{
    //        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
//        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
//        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
//    }};
    public List<Map<String, String>> myListFile() throws IOException,NoSuchFileException {

        String logoMy=animalsPath+"\\"+"cat";
        Path dir = Paths.get(logoMy);
        DirectoryStream<Path> paths = Files.newDirectoryStream(dir);
        List<Map<String, String>> fil = new ArrayList<Map<String, String>>();
        for (Path file : paths) {
//            String fileName=file.toString();
//            int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
//            fileName = fileName.substring(startIndex + 1);
            String nam=file.toString();
            HashMap<String, String> fileList=new HashMap<String, String>(){{put("id",String.valueOf(counter++));put("name", nam);}};
            // System.out.println(file);

            fil.add(fileList);
            // counter++;
        }
        return fil;
    }
    @GetMapping
    public List<Map<String, String>> list() throws IOException {
        if(messages.size()>0){}
        else{messages=myListFile();}

        return messages;
    }
    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }


}
