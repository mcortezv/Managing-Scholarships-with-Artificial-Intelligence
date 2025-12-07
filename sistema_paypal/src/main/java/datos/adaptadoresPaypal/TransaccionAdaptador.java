package datos.adaptadoresPaypal;

import datos.dominioPaypal.Transaccion;
import datos.repositoryPaypal.documents.TransaccionDocument;

public class TransaccionAdaptador {

    public static Transaccion toEntity(TransaccionDocument document) {
        if (document == null) {
            return null;
        }
        Transaccion entity = new Transaccion();
        entity.setId(document.getId());
        entity.setFolio(document.getFolio());
        entity.setFecha(document.getFecha());
        entity.setMonto(document.getMonto());
        entity.setUsuarioEmail(document.getUsuarioEmail());
        entity.setDestinatario(document.getDestinatario());
        entity.setConcepto(document.getConcepto());
        entity.setEstado(document.getEstado());

        return entity;
    }

    public static TransaccionDocument toDocument(Transaccion entity) {
        if (entity == null) {
            return null;
        }

        TransaccionDocument document = new TransaccionDocument();
        document.setId(entity.getId());
        document.setFolio(entity.getFolio());
        document.setFecha(entity.getFecha());
        document.setMonto(entity.getMonto());
        document.setUsuarioEmail(entity.getUsuarioEmail());
        document.setDestinatario(entity.getDestinatario());
        document.setConcepto(entity.getConcepto());
        document.setEstado(entity.getEstado());

        return document;
    }
}