import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

import javax.net.ssl.ExtendedSSLSession;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.*;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Sint21P2 extends HttpServlet{
    private static final String password = "practica21";
    public void doGet(HttpServletRequest request, HttpServletResponse response){
            String fase = request.getParameter("fase");
            String errores = request.getParameter("errores");
            String auto = request.getParameter("auto");
            String password = request.getParameter("p");
            if(password!=null && password.equals(this.password)){
                if(movies==null)
                    iniciarDatos(request);
                if(errores!=null && errores.equals("si")){
                    if(auto==null){
                        pantallaErrores(request, response);
                    }
                    else{
                        pantallaErroresAuto(request, response);
                    }
                }
                else if(fase!=null){
                    switch (fase) {
                        case "11":
                            if(auto==null)
                                pantallaFase11(request, response);
                            else
                                pantallaFase11Auto(request, response);
                            break;
                        case "12":
                            if(auto==null)
                                pantallaFase12(request, response);
                            else
                                pantallaFase12Auto(request, response);
                            break;
                        case "13":
                            if(auto==null)
                                pantallaFase13(request, response);
                            else
                                pantallaFase13Auto(request, response);
                            break;
                        case "14":
                            if(auto==null)
                                pantallaFase14(request, response);
                            else
                                pantallaFase14Auto(request, response);
                            break;
                        default:
                            if(auto==null)
                                pantallaFase0(request, response);
                            else
                                pantallaFase0Auto(request, response);
                            break;
                    }
                }
                else{
                    if(auto==null)
                            pantallaFase0(request, response);
                        else
                            pantallaFase0Auto(request, response);
                }
            }
            else if(password==null){
                if(auto!=null && auto.equals("si")){
                    pantallasPasswdNoExistenteAuto(request, response);
                }
                else{
                    pantallasPasswdNoExistente(request, response);
                }
            }
            else{
                if(auto!=null && auto.equals("si")){
                    pantallasPasswdIncorrectaAuto(request, response);
                }
                else{
                    pantallasPasswdIncorrecta(request, response);
                }
            }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        pantallaFase0(request, response);
    }

    /* **********************************************************   Funciones pantallas    ********************************************************* */

    public void pantallasPasswdNoExistente(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de peliculas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h1>No passwd</h1>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallasPasswdNoExistenteAuto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<?xml version='1.0' encoding='UTF-8'?>");
            pw.println("<wrongRequest>no passwd</wrongRequest>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallasPasswdIncorrecta(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de peliculas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h1>Bad passwd</h1>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallasPasswdIncorrectaAuto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<?xml version='1.0' encoding='UTF-8'?>");
            pw.println("<wrongRequest>bad passwd</wrongRequest>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaErrores(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de peliculas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form method='GET' action=''>");
            pw.println("<input type='hidden' name='fase'>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<div>");
            pw.println("<h1>Servicio de consulta de peliculas</h1>");
            LinkedList<String> listaWarning = new LinkedList<String>();
            if(warnings.size()!=0){
                for(int i=0; i<warnings.size(); i++){
                    String a= warnings.get(i).toString();
                    do{
                        i++;
                        if(i==warnings.size())
                            break;
                    }while(warnings.get(i).getSystemId().equals(warnings.get(i-1).getSystemId()));
                    listaWarning.add(a);
                }
            }
            pw.println("<h3>Se han encontrado "+listaWarning.size()+" ficheros con warnings</h3>");
            pw.println("<ul>");
            for(int i=0; i<listaWarning.size(); i++){
                pw.println("<li>"+listaWarning.get(i)+"</li>");
            }
            pw.println("</ul>");
            pw.println("<br>");
            LinkedList<String> listaError = new LinkedList<String>();
            if(errors.size()!=0){
                for(int i=0; i<errors.size(); i++){
                    String a = errors.get(i).toString();
                    do{
                        i++;
                        if(i==errors.size())
                            break;
                    }while(errors.get(i).getSystemId().equals(errors.get(i-1).getSystemId()));
                    listaError.add(a);
                }
            }
            pw.println("<h3>Se han encontrado "+listaError.size()+" ficheros con errores</h3>");
            pw.println("<ul>");
            for(int i=0; i<listaError.size(); i++){
                pw.println("<li>"+listaError.get(i)+"</li>");
            }
            pw.println("</ul>");
            pw.println("<br>");
            LinkedList<String> listaFError = new LinkedList<String>();
            if(ferrors.size()!=0){
                for(int i=0; i<ferrors.size(); i++){
                    String a = ferrors.get(i).toString();
                    do{
                        i++;
                        if(i==ferrors.size())
                            break;
                    }while(ferrors.get(i).getSystemId().equals(ferrors.get(i-1).getSystemId()));
                    listaFError.add(a);
                }
            }
            pw.println("<h3>Se han encontrado "+listaFError.size()+" ficheros con errores fatales</h3>");
            pw.println("<ul>");
            for(int i=0; i<listaFError.size(); i++){
                pw.println("<li>"+listaFError.get(i).toString()+"</li>");
            }
            pw.println("</ul>");
            pw.println("<br>");
            pw.println("<input type='submit' value='Atras' onclick='document.forms[0].fase.value=0'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaErroresAuto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<?xml version='1.0' encoding='utf-8'?>");
            pw.println("<errores>");
            pw.println("<warnings>");
            for(int i=0; i<warnings.size(); i++){
                pw.println("<warning>");
                pw.println("<file>"+warnings.get(i).getSystemId()+"</file>");
                pw.println("<cause>");
                do{
                    pw.print(warnings.get(i).getMessage()+"; ");
                    i++;
                    if(i==warnings.size())
                        break;
                }while(warnings.get(i).getSystemId().equals(warnings.get(i-1).getSystemId()));
                pw.println("</cause>");
                pw.println("</warning>");
            }
            pw.println("</warnings>");
            pw.println("<errors>");
            for(int i=0; i<errors.size(); i++){
                pw.println("<error>");
                pw.println("<file>"+errors.get(i).getSystemId()+"</file>");
                pw.println("<cause>");
                do{
                    pw.print(errors.get(i).getMessage()+"; ");
                    i++;
                    if(i==errors.size())
                        break;
                }while(errors.get(i).getSystemId().equals(errors.get(i-1).getSystemId()));
                pw.println("</cause>");
                pw.println("</error>");
            }
            pw.println("</errors>");
            pw.println("<fatalerrors>");
            for(int i=0; i<ferrors.size(); i++){
                pw.println("<fatalerror>");
                pw.println("<file>"+ferrors.get(i).getSystemId()+"</file>");
                pw.println("<cause>");
                do{
                    pw.print(ferrors.get(i).getMessage()+"; ");
                    i++;
                    if(i==ferrors.size())
                        break;
                }while(ferrors.get(i).getSystemId().equals(ferrors.get(i-1).getSystemId()));
                pw.println("</cause>");
                pw.println("</fatalerror>");
            }
            pw.println("</fatalerrors>");
            pw.println("</errores>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase0(HttpServletRequest request, HttpServletResponse response){
         try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<title>Servicio de consulta de peliculas</title>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form method='GET' action=''>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<div>");
            pw.println("<input type='hidden' name='errores'>");
            pw.println("<h1>Servicio de consulta de películas</h1>");
            pw.println("<h1>Bienvenido a este servicio</h1>");
            pw.println("<input id='enlace' type='submit' value='Pulse aqui para ver los ficheros erroneos' onclick='document.forms[0].errores.value=\"si\"'>");
            pw.println("<br>");
            pw.println("<h3>Selecciona una consulta</h3>");
            pw.println("<input type='radio' checked='true' name='fase' value='11'>Consulta 1: Filmografía de un miembro del reparto");
            pw.println("<br>");
            pw.println("<input type='submit' value='Enviar'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public void pantallaFase0Auto(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<?xml version='1.0' encoding='utf-8'?>");
            pw.println("<service>");
            pw.println("<status>OK</status>");
            pw.println("</service>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pantallaFase11(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de películas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form method='GET' action=''>");
            pw.println("<input type='hidden' name='fase'>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<div>");
            pw.println("<h1>Servicio de consulta de películas</h1>");
            pw.println("<h3>Selecciona un año:</h3>");
            LinkedList<Anio> anios = getC1Anios();
            for(int i=0; i<anios.size(); i++){
                pw.println("<input type='radio' name='anio' checked='true' value='"+anios.get(i).getAnio()+"'>"+(i+1)+".-"+anios.get(i).getAnio()+"<br>");
            }
            pw.println("<input type='submit' value='Enviar' onclick='document.forms[0].fase.value=12'><br>");
            pw.println("<input type='submit' value='Atrás' onclick='document.forms[0].fase.value=0'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase11Auto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();

            LinkedList<Anio> anios = getC1Anios();

            pw.println("<?xml version='1.0' encoding='utf-8' ?>");
            pw.println("<anios>");
            for(int i=0; i<anios.size(); i++){
                pw.println("<anio>"+anios.get(i).getAnio()+"</anio>");
            }
            pw.println("</anios>");
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public void pantallaFase12(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            String anio = request.getParameter("anio");

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de peliculas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form>");
            pw.println("<input type='hidden' name='fase'>");
            pw.println("<input type='hidden' name='anio' value='"+anio+"'>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<div>");
            pw.println("<h1>Servicio de consulta de películas</h1>");
            pw.println("<h3>Año="+anio+"</h3>");
            pw.println("<h3>Selecciona una película:</h3>");
            LinkedList<Pelicula> peliculas = getC1Peliculas(anio);
            for(int i=0; i<peliculas.size(); i++){
                pw.println("<input type='radio' checked='true' name='pelicula' value='"+peliculas.get(i).getTitulo()+"'>"+
                            (i+1)+".-"+peliculas.get(i).getTitulo()+"("+peliculas.get(i).getDuracion()+".min)<br>");
            }
            if(peliculas.size()>0)
              pw.println("<input type='submit' value='Enviar' onclick='document.forms[0].fase.value=13'>");
            pw.println("<input type='submit' value='Atras' onclick='document.forms[0].fase.value=11'>");
            pw.println("<input type='submit' value='Inicio' onclick='document.forms[0].fase.value=0'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase12Auto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
             pw.println("<?xml version='1.0' encoding='utf-8'?>");
             pw.println("<peliculas>");
             LinkedList<Pelicula> peliculas = getC1Peliculas(request.getParameter("anio"));
             for(int i=0; i<peliculas.size(); i++){
                 pw.println("<pelicula duracion='"+peliculas.get(i).getDuracion()+"' langs='"+
                 peliculas.get(i).getLangs()+"'>"+peliculas.get(i).getTitulo()+"</pelicula>");
             }
             pw.println("</peliculas>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase13(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            String anio = request.getParameter("anio");
            String pelicula = request.getParameter("pelicula");

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de películas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form>");
            pw.println("<div>");
            pw.println("<input type='hidden' name='fase'>");
            pw.println("<input type='hidden' name='anio' value='"+anio+"'>");
            pw.println("<input type='hidden' name='pelicula' value='"+pelicula+"'>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<h1>Servicio de consulta de peliculas</h1>");
            pw.println("<h3>Año="+anio+",Película="+pelicula+"</h3>");
            pw.println("<h3>Selecciona un actor:</h3>");
            LinkedList<Actor> actores = getC1Actores(anio, pelicula);
            for(int i=0; i<actores.size(); i++){
                pw.println("<input type='radio' checked='true' name='act' value='"+actores.get(i).getNombre()+"'>"+(i+1)+
                            ".-"+actores.get(i).getNombre()+"("+actores.get(i).getDireccion()+")<br>");
            }
            if(actores.size()>0)
              pw.println("<input type='submit' value='Enviar' onclick='document.forms[0].fase.value=14'>");
            pw.println("<input type='submit' value='Atrás' onclick='document.forms[0].fase.value=12'>");
            pw.println("<input type='submit' value='Inicio' onclick='document.forms[0].fase.value=0'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase13Auto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<?xml version='1.0' encoding='utf-8'?>");
            LinkedList<Actor> actores = getC1Actores(request.getParameter("anio"), request.getParameter("pelicula"));
            pw.println("<reparto>");
            for(int i=0; i<actores.size(); i++){
                pw.println("<act ciudad='"+actores.get(i).getDireccion()+"'>"+actores.get(i).getNombre()+"</act>");
            }
            pw.println("</reparto>");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase14(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            String anio = request.getParameter("anio");
            String pelicula = request.getParameter("pelicula");
            String act = request.getParameter("act");

            pw.println("<!Doctype html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'/>");
            pw.println("<link rel='stylesheet' type='text/css' href='mml.css'/>");
            pw.println("<title>Servicio de consulta de películas</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class='principal'>");
            pw.println("<form>");
            pw.println("<input type='hidden' name='fase'>");
            pw.println("<input type='hidden' name='anio' value='"+anio+"'>");
            pw.println("<input type='hidden' name='pelicula' value='"+pelicula+"'>");
            pw.println("<input type='hidden' name='p' value='"+request.getParameter("p")+"'>'");
            pw.println("<div>");
            pw.println("<h1>Servicio de consulta de películas</h1>");
            pw.println("<h3>Año="+anio+",Película="+pelicula+",Act="+act+"</h3>");
            pw.println("<h3>El personaje es:"+getC1Filmografía(anio, pelicula, act).getPersonaje()+"</h3>");
            pw.println("<h3>Esta es su filmografía:</h3>");
            pw.println("<ul>");
            LinkedList<Film> filmografia = getC1Filmografía(anio, pelicula, act).getFilms();
            Collections.sort(filmografia);
            for(int i=0; i<filmografia.size(); i++){
                if(filmografia.get(i).getOscar()!=null)
                    pw.println("<li>"+(i+1)+".-<b>Título</b>="+filmografia.get(i).getTitulo()+", <b>Óscar</b>="+filmografia.get(i).getOscar()+"</li>");
                else
                    pw.println("<li>"+(i+1)+".-<b>Título</b>="+filmografia.get(i).getTitulo()+"</li>");
            }
            pw.println("</ul>");
            pw.println("<input type='submit' value='Atrás' onclick='document.forms[0].fase.value=13'>");
            pw.println("<input type='submit' value='Inicio' onclick='document.forms[0].fase.value=0'>");
            pw.println("</div>");
            pw.println("</form>");
            pw.println("<footer>");
            pw.println("<p>Diseñado por: Adrián Vázquez Saavedra</p>");
            pw.println("</footer>");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void pantallaFase14Auto(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("application/xml; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            Actor a = getC1Filmografía(request.getParameter("anio"), request.getParameter("pelicula"),
                                       request.getParameter("act"));
            pw.println("<?xml version='1.0' encoding='utf-8'?>");
            pw.println("<filmografia nombre='"+a.getNombre()+"' personaje='"+a.getPersonaje()+"'>");
            LinkedList<Film> films = a.getFilms();
            Collections.sort(films);
            for(int i=0; i<films.size(); i++){
                if(films.get(i).getOscar()!=null)
                    pw.println("<film oscar='"+films.get(i).getOscar()+"'>"+films.get(i).getTitulo()+"</film>");
                else
                    pw.println("<film>"+films.get(i).getTitulo()+"</film>");
            }
            pw.println("</filmografia>");

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    /* ***************************************************** Manejo de datos ****************************************************** */


    public LinkedList<Anio> getC1Anios(){
        LinkedList<Anio> anios = new LinkedList<Anio>();
        Set<String> keys = movies.keySet();
        for(String key: keys){
            anios.add(movies.get(key));
        }
        Collections.sort(anios);
        return anios;
    }

    public LinkedList<Pelicula> getC1Peliculas(String anio){
        LinkedList<Pelicula> peliculas = new LinkedList<Pelicula>();
        Set<String> keys = movies.get(anio).getPeliculas().keySet();
        for(String key: keys){
            peliculas.add(movies.get(anio).getPeliculas().get(key));
        }
        Collections.sort(peliculas, new ComparadorTitulo());
        Collections.sort(peliculas);
        return peliculas;
    }

    public LinkedList<Actor> getC1Actores(String anio, String pelicula){
        LinkedList<Actor> actores = new LinkedList<Actor>();
        LinkedHashMap<String, Actor> actores2 = movies.get(anio).getPeliculas().get(pelicula).getReparto();
        Set<String> keys = actores2.keySet();
        for(String key: keys){
            actores.add(actores2.get(key));
        }
        Collections.sort(actores);
        return actores;
    }

    public Actor getC1Filmografía(String anio, String pelicula, String actor){
        Actor a = movies.get(anio).getPeliculas().get(pelicula).getReparto().get(actor);
        LinkedList<Film> films = new LinkedList<Film>();
        Set<String> keysAnios = movies.keySet();
        for(String keyAnios : keysAnios){
            LinkedHashMap<String, Pelicula> peliculas = movies.get(keyAnios).getPeliculas();
            Set<String> keysPeliculas = peliculas.keySet();
            for(String keyPeliculas : keysPeliculas){
                if(peliculas.get(keyPeliculas).getReparto().get(actor)!=null){
                    if(peliculas.get(keyPeliculas).getReparto().get(actor).getOscar()!=null){
                        Film f = new Film(keyPeliculas, peliculas.get(keyPeliculas).getReparto().get(actor).getOscar());
                        films.add(f);
                    }
                    else{
                        Film f = new Film(keyPeliculas);
                        films.add(f);
                    }
                }

            }
        }
        Collections.sort(films, new ComparadorTituloFilms());
	Collections.sort(films);
        a.setFilms(films);
        return a;
    }


    /******************************************************** Manejo de datos************************************************************* */
    private static final String mml2001 = "http://gssi.det.uvigo.es/users/agil/public_html/SINT/17-18/mml2001.xml";
    private static LinkedList<String> ficherosMML;
    private static LinkedHashMap<String, Anio> movies;
    private static LinkedList<SAXParseException> warnings;
    private static LinkedList<SAXParseException> errors;
    private static LinkedList<SAXParseException> ferrors;

    public void iniciarDatos(HttpServletRequest request){
        ficherosMML = new LinkedList<String>();
        ficherosMML.add(mml2001);
        movies = new LinkedHashMap<String, Anio>();
        warnings = new LinkedList<SAXParseException>();
        errors = new LinkedList<SAXParseException>();
        ferrors = new LinkedList<SAXParseException>();
        StringBuffer url;
        java.net.URL urlServlet;
        java.net.URL urlSchema = null;
        try{
            url = request.getRequestURL();
            urlServlet = new URL(url.toString());
            urlSchema = new URL(urlServlet, "mml.xsd");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
       for(int i=0; i<ficherosMML.size(); i++){
           try{
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = sFactory.newSchema(urlSchema);
                documentBuilderFactory.setSchema(schema);
                DocumentBuilder dBuilder = documentBuilderFactory.newDocumentBuilder();
                dBuilder.setErrorHandler(new XML_ErrorHandler());
                Document document;
                if(ficherosMML.get(i).split(":")[0].equals("http")){
                    document = dBuilder.parse(ficherosMML.get(i));
                }
                else{
                    document = dBuilder.parse("http://gssi.det.uvigo.es/users/agil/public_html/SINT/17-18/"+ficherosMML.get(i));
                }
                if(errors.size()==0 && warnings.size()==0 && ferrors.size()==0)
                    anadirMML(document);
                else if((errors.size()!=0 && errors.get(errors.size()-1).getSystemId().equals(document.getDocumentURI().toString())) ||
                        (warnings.size()!=0 && warnings.get(warnings.size()-1).getSystemId().equals(document.getDocumentURI().toString())) ||
                        (ferrors.size()!=0 && ferrors.get(ferrors.size()-1).getSystemId().equals(document.getDocumentURI().toString())))
                    continue;
                else
                    anadirMML(document);
           }catch(SAXParseException e){
                e.printStackTrace();
           }catch(SAXException e){
                e.printStackTrace();
           }catch(IOException e){
                e.printStackTrace();
           }catch(ParserConfigurationException e){
                e.printStackTrace();
           }catch(XPathExpressionException e){
                e.printStackTrace();
           }
       }

    }


    public void anadirMML(Document document) throws XPathExpressionException{
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression exprt = xPath.compile("/Movies/Anio/text()");
        NodeList nl = (NodeList)exprt.evaluate(document, XPathConstants.NODESET);
        Anio anio = new Anio(nl.item(0).getNodeValue());

        exprt = xPath.compile("/Movies/Pais/@pais");
        nl = (NodeList)exprt.evaluate(document, XPathConstants.NODESET);
        for(int i=0; i<nl.getLength(); i++){
            String pais = nl.item(i).getNodeValue();
            exprt = xPath.compile("(/Movies/Pais[@pais='"+pais+"']/@lang)");
            String lang = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
            exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula/@ip");
            NodeList nl2 = (NodeList)exprt.evaluate(document, XPathConstants.NODESET);
            Pelicula[] peliculas = new Pelicula[nl2.getLength()];
            for(int j=0; j<nl2.getLength(); j++){
                String ip = nl2.item(j).getNodeValue();
                exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/@langs");
		String langs = "";
		if(((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).getLength()>0)
		    langs = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                else
		    langs = lang;
                exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Titulo/text()");
                String titulo = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Duracion/text()");
                String duracion = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Generos/Genero/text()");
                String[] generos = new String[((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).getLength()];
                for(int a=0; a<generos.length; a++){
                    generos[a] = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(a).getNodeValue();
                }
                Pelicula p = new Pelicula(pais, lang, langs, ip, titulo, generos, duracion);

                exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto/Nombre/text()");
                NodeList nl3 = (NodeList)exprt.evaluate(document, XPathConstants.NODESET);
                for(int k=0; k<nl3.getLength(); k++){
                    String nombre = nl3.item(k).getNodeValue();
                    exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/Personaje/text()");
                    String personaje = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                    exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/Oscar/text()");
                    String oscar = null;
                    if(((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).getLength()!=0)
                        oscar = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                    exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/OtraPelicula/MML/text()");
                    NodeList nl4 = (NodeList)exprt.evaluate(document, XPathConstants.NODESET);
                    String[] otraPelicula = new String[nl4.getLength()];
                    String[] otroMML = new String[nl4.getLength()];
                    for(int z=0; z<nl4.getLength(); z++) {
                        exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/OtraPelicula/ip/text()");
                        if(((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).getLength()!=0){
                            otraPelicula[z] = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                        }
                        else{
                            exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/OtraPelicula/Titulo/text()");
                            otraPelicula[z] = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue();
                        }
                        exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']/OtraPelicula/MML/text()");
                        otroMML[z] = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getNodeValue().trim();
                        boolean estaDefinido = false;
                        for(int q=0; q<ficherosMML.size(); q++){
                            if(otroMML[z].equals(ficherosMML.get(q)))
                                estaDefinido = true;
                        }
                        if(!estaDefinido)
                            ficherosMML.add(otroMML[z]);
                    }
                    exprt = xPath.compile("/Movies/Pais[@pais='"+pais+"']/Pelicula[@ip='"+ip+"']/Reparto[Nombre='"+nombre+"']");
                    NodeList todo = ((NodeList)exprt.evaluate(document, XPathConstants.NODESET)).item(0).getChildNodes();
                    String direccion = null;
                    boolean esDireccion = true;
                    for(int t=0; t<todo.getLength(); t++){
                        esDireccion = true;
                        if(t>=todo.getLength())
                            break;
                        direccion = todo.item(t).getTextContent().trim();
                        if(todo.item(t).getNodeName().equals("OtraPelicula"))
			    continue;
			if(direccion.equals(""))
                            continue;
                        if(direccion.equals(nombre.trim()))
                            continue;
                        if(direccion.equals(personaje.trim()))
                            continue;
                        if(oscar!=null && direccion.equals(oscar.trim()))
                            continue;
                        for(int z=0; z<otraPelicula.length; z++){
                            if(direccion.equals(otraPelicula[z].trim()))
                                esDireccion = false;
                            if(direccion.equals(otroMML[z].trim()))
                                esDireccion = false;
                        }
                        if(esDireccion)
                            break;
                    }
                    Actor a = new Actor(nombre, personaje, oscar, direccion, otraPelicula, otroMML);
                    p.addActor(a);
                }
                anio.addPelicula(p);
            }
        }
        movies.put(anio.getAnio(), anio);
    }

    public static class XML_ErrorHandler extends DefaultHandler{
        public void warning(SAXParseException spe){
            warnings.add(spe);
        }

        public void error(SAXParseException spe){
            errors.add(spe);
        }

        public void fatalError(SAXParseException spe){
            ferrors.add(spe);
        }

    }

}
