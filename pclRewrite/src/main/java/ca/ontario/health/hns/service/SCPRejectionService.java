package ca.ontario.health.hns.service;

import ca.ontario.health.hns.domain.SCPRejection;
import ca.ontario.health.hns.reposotories.SCPRejectionRepository;
import ca.ontario.health.hns.util.csv.CustomCsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



    @Service
    public class SCPRejectionService {
        @Autowired
        private SCPRejectionRepository scpRejectionRepository;




        public List<SCPRejection> getAllSCPRejections() {



                List<SCPRejection> scpRejections  = new ArrayList<>();



                persist();

            scpRejectionRepository.findAll()
                             .forEach(scpRejections::add);





            return scpRejections;

        }


        public SCPRejection getSCPRejection(String appNum){
            //return  topics.stream().filter(t-> t.getId().equals(id)).findFirst().get();
            return scpRejectionRepository.findOne(appNum);

        }

        public void addSCPRejection(SCPRejection scpRejection){
           // scpRejectionRepository.save()

            scpRejectionRepository.save(scpRejection) ;
        }

       /* public void updateSCPRejection( SCPRejection scpRejection) {
       *//* for (int i=0 ; i< topics.size(); i++){
            Topic t = topics.get(i);
            if (t.getId().equals(id)){
                topics.set(i,topic);
                return;
            }
        }*//*
            scpRejectionReposotory.save(scpRejection) ;
        }*/
      // @Modifying(clearAutomatically = true)
       private void persist() {

           String file = "src/main/resources/Scprejl2009110213.txt";  // with comma
          List<String> rej = new ArrayList<String>();
          rej.add("recType");
           rej.add("appNum");
           rej.add("contactName");
           rej.add("lang");
           rej.add("add1");
           rej.add("add2");
           rej.add("city");
           rej.add("prov");
           rej.add("postCode");
           rej.add("country");
           rej.add("benefitYear");
           rej.add("memCount");
           rej.add("covEndDate");
           rej.add("thersholdAmt");
           rej.add("deductAmt");
           rej.add("ceilingAmt");
           rej.add("probCodes");



           CustomCsvReader<SCPRejection> reader = new CustomCsvReader<SCPRejection>(SCPRejection.class, file, true)

                   .setOrder(rej)
                   .read();

           scpRejectionRepository.save(reader.getData() );
       return  ;

       }


    }

