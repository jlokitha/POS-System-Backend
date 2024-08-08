package lk.ijse.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    private final CustomerBO customerBO =
            (CustomerBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER );
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void init() throws ServletException {
        logger.info("CustomerController Initialized");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            String id = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();

            if (id != null) {
                getCustomerById(resp, id, writer, jsonb);
            } else {
                getAllCustomers(resp, writer, jsonb);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage());
        }
    }
    private void getAllCustomers(HttpServletResponse resp, PrintWriter writer, Jsonb jsonb) {
        try {
            List<CustomerDTO> customers = customerBO.findAllCustomers();

            if (customers != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(customers, writer);
                logger.info("Customers Found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Customers not found");
                logger.warn("Customers not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    private void getCustomerById(HttpServletResponse resp, String id, PrintWriter writer, Jsonb jsonb) {
        try {
            int cusId = Integer.parseInt(id);
            CustomerDTO customerDTO = customerBO.findCustomerById(cusId);

            if (customerDTO != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(customerDTO, writer);
                logger.info("Customer found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Customer not found");
                logger.warn("Customer not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            logger.error("Invalid Request");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO dto = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            boolean saved = customerBO.saveCustomer(dto);

            if (saved) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Customer saved successfully");
                logger.info("Customer saved successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Customer not saved");
                logger.warn("Customer not saved");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            logger.error("Invalid Request");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO dto = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            boolean updated = customerBO.updateCustomer(dto);

            if (updated) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Customer updated successfully");
                logger.info("Customer updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Customer not updated");
                logger.warn("Customer not updated");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            int cusId = Integer.parseInt(req.getParameter("id"));
            boolean deleted = customerBO.deleteCustomer(cusId);

            if (deleted) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Customer deleted successfully");
                logger.info("Customer deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Customer not deleted");
                logger.warn("Customer not deleted");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
