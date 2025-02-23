package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.InspectionRecords;
import dao.InspectionRecordsDAO;
import dao.InspectionStationsDAO;
import dao.VehiclesDAO;
import java.util.ArrayList;
import model.InspectionStations;
import model.Vehicles;

public class InspectionRecordsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InspectionRecordsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InspectionRecordsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            if (action.equals("viewInspectionDate")) {
//                String id = request.getParameter("vehicleid");
//                int vehicleid = Integer.parseInt(id);

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);

//                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
//                int Userid = vehicle.getOwnerID();
//                request.setAttribute("Userid", Userid);
                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);
            }
        }
        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            if (action.equals("addRegistCar")) {
                String id = request.getParameter("vehicleid");
                int vehicleid = Integer.parseInt(id);

                String sId = request.getParameter("stationId");
                int stationId = Integer.parseInt(sId);

                String date = request.getParameter("RegistrationDate");
                Date RegistrationDate = java.sql.Date.valueOf(date);

                // check date
                Date today = new Date(System.currentTimeMillis());
                if (RegistrationDate.before(today)) {
                    request.setAttribute("errorMessage", "Invalid registration date "+RegistrationDate +" !");
                    
                    request.setAttribute("stationId", stationId);
                    request.setAttribute("RegistrationDate", RegistrationDate);
                    Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                    vehicle.includenspectionRecords();
                    request.setAttribute("vehicle", vehicle);
                    ArrayList<InspectionStations> listStation = InspectionStationsDAO.getStations();
                    request.setAttribute("listStation", listStation);
                    request.getRequestDispatcher("view/VehicleResult.jsp").forward(request, response);
                    return;
                }

                int defaultInspectorId = 1;
                String result = "Pending";
                double co2 = 0.0;
                double hc = 0.0;

                InspectionRecords record = new InspectionRecords(vehicleid, stationId, defaultInspectorId, RegistrationDate, result, co2, hc);

                InspectionRecordsDAO.addRecordsforOwner(record);
                // list records
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);

                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
