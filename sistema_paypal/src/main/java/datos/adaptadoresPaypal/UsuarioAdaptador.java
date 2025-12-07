package datos.adaptadoresPaypal;

import datos.dominioPaypal.Usuario;
import datos.repositoryPaypal.documents.UsuarioDocument;

public class UsuarioAdaptador {
    public static Usuario toEntity(UsuarioDocument doc) {
        if (doc == null) return null;
        Usuario entity = new Usuario();
        entity.setId(doc.getId());
        entity.setNombre(doc.getNombre());
        entity.setEmail(doc.getEmail());
        entity.setPassword(doc.getPassword());
        entity.setSaldo(doc.getSaldo());
        return entity;
    }

    public static UsuarioDocument toDocument(Usuario entity) {
        if (entity == null) return null;
        UsuarioDocument doc = new UsuarioDocument();
        doc.setId(entity.getId());
        doc.setNombre(entity.getNombre());
        doc.setEmail(entity.getEmail());
        doc.setPassword(entity.getPassword());
        doc.setSaldo(entity.getSaldo());

        return doc;
    }
}
