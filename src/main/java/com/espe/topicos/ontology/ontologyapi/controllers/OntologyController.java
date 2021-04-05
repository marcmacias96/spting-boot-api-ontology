package com.espe.topicos.ontology.ontologyapi.controllers;
import com.espe.topicos.ontology.ontologyapi.services.JenaWork;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
public class OntologyController {


    @CrossOrigin
    @RequestMapping("/getuniversities")
    public List<JSONObject> getUniversities(){

        String queryString =
                "PREFIX as: <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                "SELECT ?Unversidades\n" +
                        " WHERE { ?nombre as:nombre_Universidad ?Unversidades}";
        List<JSONObject> resultSet = JenaWork.getItems1(queryString,"Unversidades");
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getcareers")
    public List<JSONObject> getCareers(@RequestParam String university) {

        String queryString =
                "PREFIX as: <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                "SELECT ?Carreras\n" +
                        " WHERE { ?u as:TieneSedes ?s .\n" +
                        " ?s as:SedeTieneCarrerasUniv ?c.\n" +
                        " ?c as:nombre_Carrera?Carreras.\n" +
                        " ?u as:nombre_Universidad?Universidad\n" +
                        "FILTER regex(?Universidad , \""+ university +"\") }";
        List<JSONObject> resultSet = JenaWork.getItems1(queryString, "Carreras");
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/careerinfo")
    public List<JSONObject> careerInfo(@RequestParam String career) {

        String queryString =
                "PREFIX as: <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                "SELECT ?NombreCarrera ?Cupos ?PuntajeMinimo\n" +
                        " WHERE { \n" +
                        " ?n as:nombre_Carrera ?NombreCarrera .\n" +
                        "?s as:cupos_Carrera ?Cupos .\n" +
                        "?p as:puntajeMinimo_Carrera ?PuntajeMinimo\n" +
                        "FILTER regex(?NombreCarrera , \""+career+"\") }\n";
        System.out.println(queryString);
        List<JSONObject> resultSet = JenaWork.getItems3(queryString, "NombreCarrera", "Cupos", "PuntajeMinimo");

        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getstudents")
    public List<JSONObject> getStudents() {

        String queryString =
                "PREFIX as: <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                "SELECT ?nombre ?apellido ?cedula\n" +
                        "  WHERE {\n" +
                        "          \t ?x as:nombres_Persona ?nombre .\n" +
                        "          \t?x as:apellidos_Persona ?apellido.\n" +
                        "\t?x as:cedula_Persona ?cedula}\n";
        System.out.println(queryString);
        List<JSONObject> resultSet = JenaWork.getItems3(queryString, "nombre", "apellido","cedula");

        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getstudent")
    public List<JSONObject> getStudent(@RequestParam String id) {

        String queryString =
                "PREFIX as: <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                        "SELECT ?Aspirante ?Cedula ?Puntaje\n" +
                        "  WHERE {   ?a as:Postular ?p .\n" +
                        "        \t?a as:nombres_Persona ?Aspirante.\n" +
                        "        \t?a as:cedula_Persona ?Cedula.\n" +
                        "        \t?p as:puntajeEAES_Registro ?Puntaje\n" +
                        "        \tFILTER regex(?Cedula , \""+id+"\") }\n";
        System.out.println(queryString);
        List<JSONObject> resultSet = JenaWork.getItems3(queryString, "Aspirante", "Cedula","Puntaje");

        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getnominations")
    public List<JSONObject> getNominations(@RequestParam String id) {

        String queryString =
                "PREFIX as:  <http://www.semanticweb.org/darex/ontologies/2021/2/untitled-ontology-28#>\n" +
                        "SELECT ?Universidad ?Carreras\n" +
                        "  WHERE {\n" +
                        "?a as:Postular ?r.\n" +
                        "?r as:TieneOpciones ?p.\n" +
                        "?p as:TieneUniversidades ?u.\n" +
                        " ?u as:TieneSedes ?s .\n" +
                        "  ?s as:SedeTieneCarrerasUniv ?c.\n" +
                        "  ?c as:nombre_Carrera?Carreras.\n" +
                        "  ?u as:nombre_Universidad?Universidad.\n" +
                        "?a as:cedula_Persona ?Cedula\n" +
                        "FILTER regex(?Cedula ,\""+id+"\")\n" +
                        "\n" +
                        " }";
        System.out.println(queryString);
        List<JSONObject> resultSet = JenaWork.getItems2(queryString, "Universidad", "Carreras");

        return resultSet;
    }
}
