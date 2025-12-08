/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.daoGobierno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.excepcionesGobierno.BecaDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import java.util.ArrayList;
import java.util.List;
import datosGobierno.dominioGobierno.Beca;
import gobierno.RequisitosDTOGobierno;
import org.bson.conversions.Bson;
import solicitarBeca.dominio.BecasFiltradas;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BecaDAO implements IBecaDAO{
    private final MongoCollection<Beca> col;
    
    public BecaDAO(){
        this.col = MongoClienteProvider.INSTANCE.getCollection("becas", Beca.class);
    }

    @Override
    public List<Beca> findByRequisitos(RequisitosDTOGobierno r){
        try{
            BecasFiltradas bf = new BecasFiltradas();
            List<Beca> resultado = new ArrayList<>();
            Bson filtro = Filters.and(
                    Filters.lte("requisitos.promedioMinimo", r.getPromedioMinimo()),
                    Filters.gte("requisitos.ingresoFamiliarMaximo", r.getIngresoFamiliarMaximo()),
                    Filters.lte("requisitos.cargaAcademica", r.getCargaAcademica()),
                    Filters.eq("requisitos.trabajo", r.isTrabajo()),
                    Filters.eq("requisitos.deudas", r.isDeudas())
            );
            col.find(filtro).into(resultado);
            return resultado;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new BecaDAOException("Error al buscar becas con los requisitos proporcionados.");
        }
    }

    @Override
    public List<Beca> obtenerBecasConSolicitudes() {
        try {
            List<Beca> resultado = new ArrayList<>();

            col.aggregate(List.of(
                    Aggregates.lookup(
                            "solicitudes",
                            "tipo",
                            "beca.tipo",
                            "solicitudes"
                    ),
                    Aggregates.match(Filters.ne("solicitudes", List.of()))
            )).into(resultado);
            return resultado;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BecaDAOException("Error al obtener becas con solicitudes.");
        }
    }

}
