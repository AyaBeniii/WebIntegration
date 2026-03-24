package org.example.resource;

import org.example.model.Sale;
import org.example.service.SaleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import io.swagger.annotations.*;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/sales", tags = {"Sales"}, description = "API REST de gestion des ventes")
public class SaleResource {

    private SaleService saleService = SaleService.getInstance();

    @GET
    @ApiOperation(value = "Lister toutes les ventes", response = Sale.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Liste des ventes récupérée avec succès")
    })
    public Response getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return Response.ok(sales).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Récupérer une vente par ID", response = Sale.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vente trouvée"),
            @ApiResponse(code = 404, message = "Vente non trouvée")
    })
    public Response getSaleById(@ApiParam(value = "ID de la vente", required = true) @PathParam("id") Long id) {
        Sale sale = saleService.getSaleById(id);

        if (sale == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok(sale).build();
    }

    @POST
    @ApiOperation(value = "Ajouter une nouvelle vente", response = Sale.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vente créée avec succès"),
            @ApiResponse(code = 400, message = "Données invalides")
    })
    public Response createSale(@ApiParam(value = "Objet vente à créer", required = true) Sale sale) {
        if (sale.getProduct() == null || sale.getProduct().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le nom du produit est obligatoire\"}")
                    .build();
        }

        Sale created = saleService.addSale(sale);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Mettre à jour une vente", response = Sale.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vente mise à jour avec succès"),
            @ApiResponse(code = 404, message = "Vente non trouvée")
    })
    public Response updateSale(
            @ApiParam(value = "ID de la vente", required = true) @PathParam("id") Long id,
            @ApiParam(value = "Nouvelles données de la vente", required = true) Sale sale) {

        Sale updated = saleService.updateSale(id, sale);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Supprimer une vente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vente supprimée avec succès"),
            @ApiResponse(code = 404, message = "Vente non trouvée")
    })
    public Response deleteSale(@ApiParam(value = "ID de la vente", required = true) @PathParam("id") Long id) {
        boolean deleted = saleService.deleteSale(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok("{\"message\": \"Vente supprimée avec succès\"}").build();
    }

    @GET
    @Path("/count")
    @ApiOperation(value = "Compter le nombre total de ventes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nombre de ventes récupéré avec succès")
    })
    public Response countSales() {
        int count = saleService.countSales();
        return Response.ok("{\"count\": " + count + "}").build();
    }
}