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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    private final ItemBO itemBO =
            (ItemBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ITEM );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {

            String id = req.getParameter("id");

            Jsonb jsonb = JsonbBuilder.create();

            if (id != null) {
                System.out.println("by id");
                getItemById(resp, id, writer, jsonb);
            } else {
                System.out.println("all");
                getAllItems(resp, writer, jsonb);
            }

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void getAllItems(HttpServletResponse resp, PrintWriter writer, Jsonb jsonb) {
        List<ItemDTO> items = itemBO.findAllItems();

        if (items != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(items, writer);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writer.write("Items not found");
        }
    }

    private void getItemById(HttpServletResponse resp, String id, PrintWriter writer, Jsonb jsonb) {
        int itemId = Integer.parseInt(id);

        ItemDTO dto = itemBO.findItemById(itemId);

        if (dto != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(dto, writer);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writer.write("Item not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            System.out.println("Invalid request");
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
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not saved");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();

            ItemDTO dto = jsonb.fromJson(req.getReader(), ItemDTO.class);

            boolean updated = itemBO.updateItem(dto);

            if (updated) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not updated");
            }
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
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Item not deleted");
            }
        }
    }
}
