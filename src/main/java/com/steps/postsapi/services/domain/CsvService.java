package com.steps.postsapi.services.domain;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.application.CreatePostApplicationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class CsvService {

    public static final String PATH_TO_CSV_FILE = "src/main/resources/static/posts.csv";
    private final static Logger logger = LoggerFactory.getLogger(CsvService.class);

    @Autowired
    private CreatePostApplicationService createPostApplicationService;

    @PostConstruct
    public void readCsvFile(){
        logger.info("Populate posts from csv file");
        extractPostFromCsv();
    }

    private void extractPostFromCsv() {
        Reader fr = null;
        try {
            fr = new FileReader(PATH_TO_CSV_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fr);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        int recordsCount = 0;
        for (CSVRecord record : records) {
            populateRecord(record);
            recordsCount++;
        }
        logger.info("Finish reading " + recordsCount + " records from csv file.");
    }

    private void populateRecord(CSVRecord record) {
        Post post = new Post(record.get("title"), record.get("body"));
        User user = new User(Long.parseLong(record.get("id")),record.get("firstName"), record.get("lastName"));

        createPostApplicationService.createPostApplicationService( user, post );
    }
}
