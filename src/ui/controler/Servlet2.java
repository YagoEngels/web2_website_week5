package ui.controler;
import domain.database.DatabankenPlayers;
import domain.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet")
public class Servlet2 extends HttpServlet {

    public Servlet2(){

    }

    DatabankenPlayers databankenPlayers = new DatabankenPlayers();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        giverOfTasks(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        giverOfTasks(request,response);
    }

    private void giverOfTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String task = "home";
        if (request.getParameter("task") != null){
            task = request.getParameter("task");
        }

        String a = "";
        switch (task){
            case "home":
                a = home(request,response);
                break;
            case "overview":
                a = overview(request,response);
                break;
            case "delete":
                a = delete(request,response);
                break;
            case "add":
                a = add(request,response);
                break;
            case "find":
                a = find(request,response);
        }
        request.getRequestDispatcher(a).forward(request, response);
    }

    private String home (HttpServletRequest request, HttpServletResponse response){
        return "index.html";
    }

    private String overview (HttpServletRequest request, HttpServletResponse response){

        request.setAttribute("db",databankenPlayers.getSpelers());
        request.setAttribute("spelersbelgie",databankenPlayers.playersFromBelgium());

        return "lijst.jsp";
    }

    private String delete (HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("deleted"));
        Player player = databankenPlayers.findPlayer((String)request.getParameter("deleted"));
        ArrayList arrayList = databankenPlayers.getSpelers();
        arrayList.remove(player);

        request.setAttribute("db",arrayList);
        request.setAttribute("spelersbelgie",databankenPlayers.playersFromBelgium());

        return "lijst.jsp";
    }

    private String add (HttpServletRequest request, HttpServletResponse response){
        String naam = request.getParameter("naam");
        String nationaliteit = request.getParameter("nationaliteit");
        String team = request.getParameter("team");

        Player player = new Player(naam,nationaliteit,team);
        databankenPlayers.addPlayer(player);
        request.setAttribute("db",databankenPlayers.getSpelers());
        request.setAttribute("spelersbelgie",databankenPlayers.playersFromBelgium());
        return "lijst.jsp";
    }

    private String find(HttpServletRequest request, HttpServletResponse response){
        String naam = request.getParameter("naam");
        String p1 = "de speler dat u zoekt : ";
        String p2 = "bestaat niet ";
        for (int i = 0; i < databankenPlayers.getSpelers().size(); i++){
            if (databankenPlayers.getSpelers().get(i).getNaam().equals(naam)){
                p2 = "is " + naam + " en heeft als land van afkomst "+ databankenPlayers.getSpelers().get(i).getNationaliteit() + " en zit in " + databankenPlayers.getSpelers().get(i).getTeam();
            }
        }
        request.setAttribute("p1" , p1);
        request.setAttribute("p2" , p2);
        request.setAttribute("db",databankenPlayers.getSpelers());
        request.setAttribute("spelersbelgie",databankenPlayers.playersFromBelgium());
        return "find_1.jsp";
    }

}
