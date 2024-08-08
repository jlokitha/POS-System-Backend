package lk.ijse.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    private final ItemBO itemBO =
            (ItemBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ITEM );
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Override
    public void init() {
        logger.info("ItemController Initialized");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            String id = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();

            if (id != null) {
                getItemById(resp, id, writer, jsonb);
            } else {
                getAllItems(resp, writer, jsonb);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage());
        }
    }
    private void getAllItems(HttpServletResponse resp, PrintWriter writer, Jsonb jsonb) {
        try {
            List<ItemDTO> items = itemBO.findAllItems();

            if (items != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(items, writer);
                logger.info("Items found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Items not found");
                logger.warn("Items not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    private void getItemById(HttpServletResponse resp, String id, PrintWriter writer, Jsonb jsonb) {
        try {
            int itemId = Integer.parseInt(id);
            ItemDTO dto = itemBO.findItemById(itemId);

            if (dto != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                jsonb.toJson(dto, writer);
                logger.info("Item found");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer.write("Item not found");
                logger.warn("Item not found");
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
            ItemDTO dto = jsonb.fromJson(req.getReader(), ItemDTO.class);
            boolean saved = itemBO.saveItem(dto);

            if (saved) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item saved successfully");
                logger.info("Item saved successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not saved");
                logger.warn("Item not saved");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            logger.error("Invalid request");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO dto = jsonb.fromJson(req.getReader(), ItemDTO.class);
            boolean updated = itemBO.updateItem(dto);

            if (updated) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item updated successfully");
                logger.info("Item updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not updated");
                logger.warn("Item not updated");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            int itemId = Integer.parseInt(req.getParameter("id"));
            boolean deleted = itemBO.deleteItem(itemId);

            if (deleted) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item deleted successfully");
                logger.info("Item deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not deleted");
                logger.warn("Item not deleted");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
