package com.filip.machaj.demo.controllers

import com.filip.machaj.demo.controllers.queryobjests.ObywatelByImie
import com.filip.machaj.demo.dto.ObywatelDTO
import com.filip.machaj.demo.model.dane.Obywatel
import com.filip.machaj.demo.service.ObywatelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/obywatel")
@CrossOrigin(origins = ["http://localhost:63342/"], maxAge = 3600, allowedHeaders = ["*"], allowCredentials = "true" )

class ObywatelController {
    @Autowired
    private lateinit var service : ObywatelService

    @GetMapping("/widok")
    fun getWidok():String{
        /*return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Zmień</title>\n" +
                "\n" +
                "    <script src=\"http://szuflandia.pjwstk.edu.pl/~s13033/handlebars-v4.1.2.js\"></script>\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div id = \"obywatel\"></div>\n" +
                "<div id = \"obywatel2\"></div>\n" +
                "<!--<form id = \"formularz\">\n" +
                "  nr dowodu: <br>\n" +
                "  <input type = \"text \" name = \"nr_dowodu\" ><br>\n" +
                "  imie: <br>\n" +
                "  <input type = \"text \" name = \"imie\" ><br>\n" +
                "  nazwisko: <br>\n" +
                "  <input type = \"text \" name = \"nazwisko\" ><br>\n" +
                "  adres: <br>\n" +
                "  <input type = \"text \" name = \"adres\" ><br>\n" +
                "  data urodzenia: <br>\n" +
                "  <input type = \"date \" name = \"data_urodzenia\" ><br>\n" +
                "  pesel:<br>\n" +
                "  <input type = \"text \" name = \"pesel\" ><br><br>\n" +
                "  <input type = \"submit\" value = \"Zatwierdź\">\n" +
                "\n" +
                "\n" +
                "</form>-->\n" +
                "<div id = \"formularz\"></div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    var obywatelDane = \"<p>identyfikator to: {{id}}   data utworzenia to:   {{kiedy_utworzono}}   \" +\n" +
                "        \"data ostatniej modyfikacji to   {{kiedy_zmodyfikowano}}   numer dowodu    {{nr_dowodu}}   imie to   {{imie}}   \" +\n" +
                "        \"nazwisko to   {{nazwisko}}   adres to   {{adres}}   aktualny to    {{czy_zarchiwizowany}}   \" +\n" +
                "        \"data urodzenia to    {{data_urodzenia}}   pesel to   {{pesel}}    </p>\";\n" +
                "\n" +
                "    template = Handlebars.compile(obywatelDane);\n" +
                "\n" +
                "\n" +
                "    var son = {\n" +
                "        id: 1,\n" +
                "        kiedy_utworzono: \"2019-08-09T16:19:41.571+0000\",\n" +
                "        kiedy_zmodyfikowano: \"2019-08-09T16:19:41.571+0000\",\n" +
                "        nr_dowodu: \"CCH864397\",\n" +
                "        imie: \"Janusz\",\n" +
                "        nazwisko: \"Korwin-Mikke\",\n" +
                "        adres: \"Woronicza 16 Warszawa \",\n" +
                "        czy_zarchiwizowany: false,\n" +
                "        data_urodzenia: \"1933-02-25\",\n" +
                "        pesel: \"95101303798\"\n" +
                "    };\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    var formularz = \"<form id = 'ff' >\\n\" +\n" +
                "        \"    nr dowodu: <br>\\n\" +\n" +
                "        \"    <input id =\\\"in1\\\" type = \\\"text \\\" name = \\\"nr_dowodu\\\" value =  {{nr_dowodu}} ><br>\\n\" +\n" +
                "        \"    imie: <br>\\n\" +\n" +
                "        \"    <input id =\\\"in2\\\" type = \\\"text \\\" name = \\\"imie\\\"  value={{imie}}><br>\\n\" +\n" +
                "        \"    nazwisko: <br>\\n\" +\n" +
                "        \"    <input id =\\\"in3\\\" type = \\\"text \\\" name = \\\"nazwisko\\\" value={{nazwisko}} ><br>\\n\" +\n" +
                "        \"    adres: <br>\\n\" +\n" +
                "        \"    <input id =\\\"in4\\\" type = \\\"text \\\" name = \\\"adres\\\" value = {{adres}} ><br>\\n\" +\n" +
                "        \"    data urodzenia: <br>\\n\" +\n" +
                "        \"    <input id =\\\"in5\\\" type = \\\"date \\\" name = \\\"data_urodzenia\\\" value = {{data_urodzenia}}  ><br>\\n\" +\n" +
                "        \"    pesel:<br>\\n\" +\n" +
                "        \"    <input id =\\\"in6\\\"type = \\\"text \\\" name = \\\"pesel\\\" value = {{pesel}}><br><br>\\n\" +\n" +
                "        \"    <input type = \\\"button\\\" value = \\\"Zatwierdź\\\" onclick='f3(obywatelDane, son)'>\\n\" +\n" +
                "        \"\\n\" +\n" +
                "        \"\\n\" +\n" +
                "        \"  </form>\";\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    function sleep(milliseconds) {\n" +
                "        var start = new Date().getTime();\n" +
                "        for (var i = 0; i < 1e7; i++) {\n" +
                "            if ((new Date().getTime() - start) > milliseconds){\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    function getModificationTime() {\n" +
                "\n" +
                "\n" +
                "        var today = new Date();\n" +
                "        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();\n" +
                "        var time = today.getHours() + \":\" + today.getMinutes() + \":\" + today.getSeconds();\n" +
                "        var dateTime = date+' '+time;\n" +
                "\n" +
                "        //alert(dateTime)\n" +
                "\n" +
                "        return dateTime;\n" +
                "\n" +
                "    }\n" +
                "    function walidacjaPesela(pesel) {\n" +
                "        if (pesel.length !=11){\n" +
                "            alert(\"PESEL musi mieć 11 cyfr\")\n" +
                "            return false\n" +
                "        }\n" +
                "\n" +
                "        if(!(/^[0-9]*\$/.test(pesel))){\n" +
                "            alert(\"PESEL może zawierać tylko cyfry\")\n" +
                "            return false\n" +
                "        }\n" +
                "\n" +
                "        var p = []\n" +
                "\n" +
                "        for (var i =0; i<pesel.length; i++){\n" +
                "            p[i] = pesel.charCodeAt(i) - 48\n" +
                "\n" +
                "        }\n" +
                "        if ((9*p[0]+7*p[1]+3*p[2]+1*p[3]+9*p[4]+7*p[5]+3*p[6]+1*p[7]+9*p[8]+7*p[9])%10===p[10])\n" +
                "            return true\n" +
                "        else\n" +
                "            alert(\"PESEL nie jest prawidłowy\")\n" +
                "        return false\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    function walidacjaNumeruDowodu(nr_dowodu_s) {\n" +
                "\n" +
                "        var nr_dowodu = nr_dowodu_s.toUpperCase()\n" +
                "\n" +
                "\n" +
                "\n" +
                "        // nr_dowodu.replace(/\\s/g,'')\n" +
                "\n" +
                "        if((/^[A-Z]{3}[0-9]{6}\$/.test(nr_dowodu))){\n" +
                "            var nrint = []\n" +
                "\n" +
                "            nrint[0] = nr_dowodu.charCodeAt(0) - 55\n" +
                "            nrint[1] = nr_dowodu.charCodeAt(1) - 55\n" +
                "            nrint[2] = nr_dowodu.charCodeAt(2) - 55\n" +
                "\n" +
                "            for (var i = 3; i<nr_dowodu.length; i++) {\n" +
                "                nrint[i] = nr_dowodu.charCodeAt(i) - 48\n" +
                "            }\n" +
                "            nrint[0]*=7\n" +
                "            nrint[1]*=3\n" +
                "            nrint[2]*=1\n" +
                "            nrint[4]*=7\n" +
                "            nrint[5]*=3\n" +
                "            nrint[6]*=1\n" +
                "            nrint[7]*=7\n" +
                "            nrint[8]*=3\n" +
                "\n" +
                "            var suma = 0\n" +
                "\n" +
                "            for(var i = 0; i<nrint.length;i++){\n" +
                "                if (i!==3)\n" +
                "                    suma+=nrint[i]\n" +
                "            }\n" +
                "\n" +
                "            suma%=10\n" +
                "\n" +
                "            if (suma === nrint[3])\n" +
                "                return true\n" +
                "            else{\n" +
                "\n" +
                "                alert(\"Numer dowodu jest nie prawidłowy\")\n" +
                "                return false\n" +
                "            }\n" +
                "\n" +
                "\n" +
                "        }\n" +
                "        else{\n" +
                "            alert(\"Numer dowodu musi się składać z 3 wielkich liter i 6 cyfr i nie zawierać spacji\")\n" +
                "            return false\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    function f3(obywatelDane, son){\n" +
                "        if (walidacjaNumeruDowodu(document.getElementById(\"in1\").value)) {\n" +
                "            son.nr_dowodu = document.getElementById(\"in1\").value; // przetestować walidację dla numeru dowodu\n" +
                "        }\n" +
                "\n" +
                "        son.imie = document.getElementById(\"in2\").value;\n" +
                "        son.nazwisko = document.getElementById(\"in3\").value;\n" +
                "        son.adres = document.getElementById(\"in4\").value;\n" +
                "        son.data_urodzenia = document.getElementById(\"in5\").value;\n" +
                "        if (walidacjaPesela(document.getElementById(\"in6\").value)){\n" +
                "            son.pesel = document.getElementById(\"in6\").value;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "        son.kiedy_zmodyfikowano = getModificationTime();// żeby data modyfikacji była zapisywana tylko wtedy kiedy żeczywiście są jakies zmiany\n" +
                "\n" +
                "\n" +
                "\n" +
                "        template = Handlebars.compile(obywatelDane);\n" +
                "\n" +
                "        var dane2 = template(son);\n" +
                "\n" +
                "        document.getElementById(\"obywatel\").innerHTML = \"\";\n" +
                "        //sleep(10000);\n" +
                "        document.getElementById(\"obywatel\").innerHTML += dane2 + window.but3;\n" +
                "\n" +
                "        document.getElementById(\"formularz\").innerHTML = \"\";\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    function f2(obywatelDane,son,formularz) {\n" +
                "\n" +
                "\n" +
                "\n" +
                "        template = Handlebars.compile(formularz);\n" +
                "\n" +
                "        var dane = template(son);\n" +
                "\n" +
                "        template = Handlebars.compile(obywatelDane)\n" +
                "\n" +
                "        var dane2 = template(son);\n" +
                "\n" +
                "        document.getElementById(\"obywatel\").innerHTML = \"\"\n" +
                "\n" +
                "        document.getElementById(\"obywatel\").innerHTML += dane2;\n" +
                "\n" +
                "\n" +
                "\n" +
                "        document.getElementById(\"formularz\").innerHTML+=dane;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        //document.getElementById(\"obywatel\").innerHTML = \"\";\n" +
                "\n" +
                "        /// f3(obywatelDane,son)\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    template = Handlebars.compile(obywatelDane);\n" +
                "\n" +
                "    var dane = template(son);\n" +
                "\n" +
                "\n" +
                "\n" +
                "    //  var bancz = ( dane + but3 + f3(obywatelDane, son));\n" +
                "    // alert(son.pesel);\n" +
                "\n" +
                "\n" +
                "    var but3  =\"    <button type=\\\"button\\\" onclick=\\\" f2(obywatelDane,son,formularz)\\\">edytuj</button>\"\n" +
                "\n" +
                "    var bancz = ( dane + but3);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    document.getElementById('obywatel').innerHTML += bancz;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</script>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n"*/

       return """
           
           
           
           
           
           
       """.trimIndent()
    }


    @GetMapping(value = ["/download"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
  //  fun getObywatel(): Iterable<Obywatel> = service.getObywatel()
    @CrossOrigin(origins = ["http://localhost:63342/"])// zmieniono dla javascript CORS
    fun getObywatel() = service.getObywatel()

    @PutMapping(
            value = ["/insert"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)

    )
   // fun insertObywatel(@RequestBody obywatel: Obywatel) = service.insertObywatel(obywatel)

    fun insertObywatel(@RequestBody obywatel: ObywatelDTO) = service.insertObywatel(obywatel)


    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteObywatel(@PathVariable(name = "id")id:Long): Unit = service.deleteObywatel(id)
    @PostMapping(
            value = ["/update"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateObywatel(@RequestBody obywatel: ObywatelDTO): ObywatelDTO = service.updateObywatel(obywatel)

    // metody dodane przezemnie

    @PostMapping(
            value = ["/imie"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)



    )
    fun getObywatelByImie(
            @RequestBody payload: ObywatelByImie
    ):Iterable<ObywatelDTO> = service.getObywatelByImie(payload.imie)


    @GetMapping(
            value = ["/last"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getLastObywatel():Obywatel = service.findLastObywatel()
  
    /*@PostMapping(
            value = ["/nazwisko"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
   fun getObywatelByNazwisko(@RequestBody payload: OBywatelFindByNazwisko): Iterable<ObywatelDTO> = service.findByNazwisko(payload.nazwisko)*/


}