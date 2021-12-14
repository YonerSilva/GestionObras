
package com.gestionObras.util;

import com.gestionObras.entities.Insumo;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

@Component("/html/Sis_Supervisor_Pedido")
public class PedidosPdf extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        
        List<Insumo> insumos = (List<Insumo>) model.get("insumos");
        PdfPTable tablaInsumos = new PdfPTable(3);
        insumos.forEach(insumo ->{
            tablaInsumos.addCell(insumo.getDescripcion());
            tablaInsumos.addCell(String.valueOf(insumo.getCantidad()));
            tablaInsumos.addCell(String.valueOf(insumo.getPrecio()));
        });
        document.add(tablaInsumos);
    }
    
}
