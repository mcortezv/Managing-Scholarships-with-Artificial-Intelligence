package datos.adaptadoresItson.pagarAdeudo;

import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.dominioItson.pagarAdeudo.SolicitudPago;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.SolicitudPagoDocument;
import java.util.ArrayList;
import java.util.List;

public class SolicitudPagoAdaptador {

    public static SolicitudPagoDocument toDocument(SolicitudPagoDTOI dto){
        SolicitudPagoDocument document = new SolicitudPagoDocument();
        document.setTipoAdeudo(dto.getTipoAdeudo());
        document.setIdEstudiante(dto.getIdEstudiante());
        document.setReferenciaPago(dto.getReferenciaPago());
        document.setFechaPago(dto.getFechaPago());
        document.setMontoPagado(dto.getMontoPagado());
        document.setMetodoPago(dto.getMetodoPago());
        document.setEstatusPago(dto.getEstatusPago());
        document.setTipoAdeudo(dto.getTipoAdeudo());
        return document;
    }

    public static SolicitudPagoDTOI toDtoItson(SolicitudPago solicitud){
        SolicitudPagoDTOI dto = new SolicitudPagoDTOI();
        dto.setTipoAdeudo(solicitud.getTipoAdeudo());
        dto.setIdEstudiante(solicitud.getIdEstudiante());
        dto.setReferenciaPago(solicitud.getReferenciaPago());
        dto.setFechaPago(solicitud.getFechaPago());
        dto.setMontoPagado(solicitud.getMontoPagado());
        dto.setMetodoPago(solicitud.getMetodoPago());
        dto.setEstatusPago(solicitud.getEstatusPago());
        dto.setTipoAdeudo(solicitud.getTipoAdeudo());
        return dto;
    }

    public static List<SolicitudPagoDTOI> toDtoItson(List<SolicitudPago> solicitudes){
        List<SolicitudPagoDTOI> lista = new ArrayList<>();
        if (solicitudes != null) {
            for (SolicitudPago s : solicitudes) {
                lista.add(toDtoItson(s));
            }
        }
        return lista;
    }
}