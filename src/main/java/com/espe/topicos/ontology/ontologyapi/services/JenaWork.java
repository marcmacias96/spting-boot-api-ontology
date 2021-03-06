package com.espe.topicos.ontology.ontologyapi.services;


import net.minidev.json.JSONObject;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.shared.JenaException;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JenaWork {

    private static QueryExecution qe;
    private static String ontoFile = "proyecto-3p.owl";

    public static ResultSet execQuery(String queryString) {

        OntModel ontoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        try {
            InputStream in = FileManager.get().open(ontoFile);
            try {
                ontoModel.read(in, null);

                Query query = QueryFactory.create(queryString);

                //Execute the query and obtain results
                qe = QueryExecutionFactory.create(query, ontoModel);
                ResultSet results = qe.execSelect();

                // Output query results
                //ResultSetFormatter.out(System.out, results, query);

                return results;

            } catch (Exception e) {
                e.printStackTrace();
            }
            //LOGGER.info("Ontology " + ontoFile + " loaded.");
        } catch (JenaException je) {
            System.err.println("ERROR" + je.getMessage());
            je.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static List<JSONObject> findPoliticians(String queryString) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("politician_name",solution.get("Y").toString().split("#")[1]);
            list.add(obj);
        }

        // Important ??? free up resources used running the query
        //qe.close();
        return list;
    }

    public static List<JSONObject> describePoliticians(String queryString) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        while (resultSet.hasNext()) {
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            if (solution.get("Z").toString().split("#")[1].equals("NamedIndividual")){
                continue;
            }
            obj.put("property",solution.get("Y").toString().split("#")[1]);
            obj.put("object",solution.get("Z").toString().split("#")[1]);
            list.add(obj);
        }

        // Important ??? free up resources used running the query
        //qe.close();
        return list;
    }

    public static List<JSONObject> getItems3(String queryString, String select1, String select2, String select3) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put(select1,solution.get(select1).toString());
            obj.put(select2,solution.get(select2).toString());
            obj.put(select3,solution.get(select3).toString());
            list.add(obj);
        }

        // Important ??? free up resources used running the query
        //qe.close();
        return list;
    }
    public static List<JSONObject> getItems2(String queryString, String select1, String select2) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put(select1,solution.get(select1).toString());
            obj.put(select2,solution.get(select2).toString());
            list.add(obj);
        }

        // Important ??? free up resources used running the query
        //qe.close();
        return list;
    }
    public static List<JSONObject> getItems1(String queryString, String select1) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put(select1,solution.get(select1).toString());
            list.add(obj);
        }

        // Important ??? free up resources used running the query
        //qe.close();
        return list;
    }
}
