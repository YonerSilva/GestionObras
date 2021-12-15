package com.gestionObras.util;

import com.gestionObras.entities.Insumo;
import com.gestionObras.entities.Pedido;
import com.lowagie.text.*;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

@Component("html/Sis_Supervisor_Pedido")
public class PedidosSuperPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Pedido pedido = (Pedido) model.get("pedido");
        document.setPageSize(LETTER);
        document.setMargins(-2.54f, -2.54f, 60f, 2.54f);
        document.open();
        //Titulo
        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;
        Font fuenteTitulo = FontFactory.getFont(FontFactory.COURIER_BOLD,20);
        Font fuenteColumnas = FontFactory.getFont(FontFactory.TIMES_BOLD,11);
        Font fuenteCeldas = FontFactory.getFont(FontFactory.TIMES,14);
        
        celda= new PdfPCell(new Phrase("REPORTE DETALLADO DE PEDIDO",fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(15);
        
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);
        
        PdfPTable tablaInfo = new PdfPTable(1);
        celda= new PdfPCell(new Phrase("*INFORMACIÓN DEL PEDIDO:",fuenteTitulo));
        celda.setBorder(0);
        tablaInfo.addCell(celda);
        tablaInfo.setSpacingAfter(20);
        
        PdfPTable tablaInfoIn = new PdfPTable(1);
        celda= new PdfPCell(new Phrase("*LISTADO DE INSUMOS DEL PEDIDO:",fuenteTitulo));
        celda.setBorder(0);
        tablaInfoIn.addCell(celda);
        tablaInfoIn.setSpacingAfter(20);
       
        //PARA LA TABLA DE PEDIDO
        PdfPTable tablaPedido = new PdfPTable(7);
        tablaPedido.setSpacingAfter(30);
        tablaPedido.setWidths(new float[]{0.4f, 1.4f, 1.7f, 1.4f, 1.4f, 1.2f, 0.8f});
        
        celda= new PdfPCell(new Phrase("ID",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("NOMBRE",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("TIPO DE PEDIDO",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("FECHA DE CARGA",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("FECHA DE APROBACIÓN",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("ESTADO DE PEDIDO",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase("TOTAL",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase((String.valueOf(pedido.getId_pedido())),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase((pedido.getNombre()),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase((pedido.toString()),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase((pedido.getFecha_carga().toString()),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        if (pedido.getFecha_aprobacion() != null) {
            celda= new PdfPCell(new Phrase((pedido.getFecha_aprobacion().toString()),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaPedido.addCell(celda);
        } else {
            celda= new PdfPCell(new Phrase(("---"),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaPedido.addCell(celda);
        }
        celda= new PdfPCell(new Phrase((pedido.getEstado()),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
        
        celda= new PdfPCell(new Phrase((String.valueOf(pedido.getTotal_pedido())),fuenteCeldas));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaPedido.addCell(celda);
     
        //PARA LA TABLA DEL INTERVENTOR
        PdfPTable tablaInterventor = new PdfPTable(2);
        
        PdfPTable tablaInterInfo = new PdfPTable(1);
        celda= new PdfPCell(new Phrase("*INFORMACIÓN DEL INTERVENTOR:",fuenteTitulo));
        celda.setBorder(0);
        tablaInterInfo.addCell(celda);
        tablaInterInfo.setSpacingAfter(20);
        
        celda= new PdfPCell(new Phrase("ID INTERVENTOR",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInterventor.addCell(celda);
        
        celda= new PdfPCell(new Phrase("NOMBRE INTERVENTOR",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInterventor.addCell(celda);
        
        if (pedido.getInterventor() != null) {
            celda= new PdfPCell(new Phrase((String.valueOf(pedido.getInterventor().getId_usuario())),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInterventor.addCell(celda);
            
            celda= new PdfPCell(new Phrase((pedido.getInterventor().getNombre() + " " + pedido.getInterventor().getApellido()),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInterventor.addCell(celda);
        } else {
            celda= new PdfPCell(new Phrase(("---"),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInterventor.addCell(celda);
            
            celda= new PdfPCell(new Phrase(("No hay interventor"),fuenteCeldas));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInterventor.addCell(celda);
        }
        
        //PARA LA TABLA DE LOS INSUMOS
        List<Insumo> insumos = (List<Insumo>) model.get("insumos");
        PdfPTable tablaInsumos = new PdfPTable(4);
        tablaInsumos.setSpacingAfter(30);
        
        celda= new PdfPCell(new Phrase("DESCRIPCIÓN",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInsumos.addCell(celda);
        
        celda= new PdfPCell(new Phrase("CANTIDAD",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInsumos.addCell(celda);
        
        celda= new PdfPCell(new Phrase("PRECIO UNITARIO",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInsumos.addCell(celda);
        
        celda= new PdfPCell(new Phrase("PUNTO",fuenteColumnas));
        celda.setBackgroundColor(Color.decode("#00A8CD"));
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaInsumos.addCell(celda);
        
        for(Insumo insumo : insumos){
           celda= new PdfPCell(new Phrase(insumo.getDescripcion(),fuenteCeldas));
           celda.setHorizontalAlignment(Element.ALIGN_CENTER);
           tablaInsumos.addCell(celda);
           
           celda= new PdfPCell(new Phrase(String.valueOf(insumo.getCantidad()),fuenteCeldas));
           celda.setHorizontalAlignment(Element.ALIGN_CENTER);
           tablaInsumos.addCell(celda);
           
           celda= new PdfPCell(new Phrase(String.valueOf(insumo.getPrecio()),fuenteCeldas));
           celda.setHorizontalAlignment(Element.ALIGN_CENTER);
           tablaInsumos.addCell(celda);
           
           celda= new PdfPCell(new Phrase(String.valueOf(insumo.getPunto().getId_punto())+" - "+insumo.getPunto().getNombre(),fuenteCeldas));
           celda.setHorizontalAlignment(Element.ALIGN_CENTER);
           tablaInsumos.addCell(celda);
        }
       
        document.add(tablaTitulo);
        document.add(tablaInfo);
        document.add(tablaPedido);
        document.add(tablaInfoIn);
        document.add(tablaInsumos);
        document.add(tablaInterInfo);
        document.add(tablaInterventor);
    }

}
