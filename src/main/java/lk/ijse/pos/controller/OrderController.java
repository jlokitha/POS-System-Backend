package lk.ijse.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.bo.custom.impl.OrderBOImpl;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {
    private final OrderBO orderBO =
            (OrderBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ORDER );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            String id = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();
            if (id != null) {
                System.out.println("by id");
                getDetailsById(resp, id, writer, jsonb);
            } else {
                System.out.println("all");
                getAllOrders(resp, writer, jsonb);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private void getAllOrders(HttpServletResponse resp, PrintWriter writer, Jsonb jsonb) {
        List<OrderDTO> all = orderBO.findAllOrders();
        if (all != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(all, writer);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writer.write("Orders not found");
        }
    }
    private void getDetailsById(HttpServletResponse resp, String id, PrintWriter writer, Jsonb jsonb) {
        int orderId = Integer.parseInt(id);

        List<OrderDetailDTO> details = orderBO.findOrderDetailsById(orderId);
        if (details != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(details, writer);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writer.write("Order details not found");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            System.out.println("Invalid request");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            System.out.println("Request Body: " + req.getReader());
            Jsonb jsonb = JsonbBuilder.create();

            OrderDTO dto = jsonb.fromJson(req.getReader(), OrderDTO.class);

            boolean saved = orderBO.saveOrder(dto);

            if (saved) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Order saved successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Order not saved");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
