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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {
    private final OrderBO orderBO =
            (OrderBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ORDER );
    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    public void init() throws ServletException {
        logger.info("OrderController Initialized");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            String id = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();

            if (id != null) {
                getDetailsById(resp, id, writer, jsonb);
            } else {
                getAllOrders(resp, writer, jsonb);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage());
        }
    }
    private void getAllOrders(HttpServletResponse resp, PrintWriter writer, Jsonb jsonb) {
        try {
            List<OrderDTO> all = orderBO.findAllOrders();

            if (all != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(all, writer);
                logger.info("Orders found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Orders not found");
                logger.warn("Orders not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    private void getDetailsById(HttpServletResponse resp, String id, PrintWriter writer, Jsonb jsonb) {
        try {
            int orderId = Integer.parseInt(id);
            List<OrderDetailDTO> details = orderBO.findOrderDetailsById(orderId);

            if (details != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(details, writer);
                logger.info("Order details found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Order details not found");
                logger.warn("Order details not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            logger.error("Invalid request");
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
                logger.info("Order saved successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Order not saved");
                logger.warn("Order not saved");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
