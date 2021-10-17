package com.epsychiatry.utils;

import com.epsychiatry.model.facebook.AdInsightsAgeGender;
import com.epsychiatry.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class FileUtil {
    @Autowired
    private LoggedUser loggedUser;

    public void saveToText(List<AdInsightsAgeGender> adInsightsAgeGenderList, String fileName) throws IOException {
        String file = "/eps/fb/tmp/" + loggedUser.getUser().getId() +"/"+ fileName + ".txt";
        Path pathFile = Paths.get(file);

        if (!Files.exists(pathFile)) {
            Files.createFile(pathFile);
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(adInsightsAgeGenderList);
        oos.close();
    }

    public List<AdInsightsAgeGender> readFromText(String fileName) throws IOException, ClassNotFoundException {
        String file = "/eps/fb/tmp/" + loggedUser.getUser().getId() +"/"+ fileName + ".txt";
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<AdInsightsAgeGender> adInsightsAgeGenderList = (List<AdInsightsAgeGender>) ois.readObject();
        ois.close();
        return adInsightsAgeGenderList;
    }
}
