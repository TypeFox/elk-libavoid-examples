package test.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkGraphFactory;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		var message = layout();
		var window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "Layout Result", message);
		return null;
	}
	
	private String layout() {
		var engine = new RecursiveGraphLayoutEngine();
		var graph = createGraph();
		engine.layout(graph, new BasicProgressMonitor());
		var section1 = graph.getContainedEdges().get(0).getSections().get(0);
		var section2 = graph.getContainedEdges().get(1).getSections().get(0);
		
		var sb = new StringBuilder();
		sb.append("edge 1: (").append(section1.getStartX()).append(", ").append(section1.getStartY()).append(")");
		for (var bp : section1.getBendPoints()) {
			sb.append(" - (").append(bp.getX()).append(", ").append(bp.getY()).append(")");
		}
		sb.append(" - (").append(section1.getEndX()).append(", ").append(section1.getEndY()).append(")");
		
		sb.append("\n");
		sb.append("edge 2: (").append(section2.getStartX()).append(", ").append(section2.getStartY()).append(")");
		for (var bp : section2.getBendPoints()) {
			sb.append(" - (").append(bp.getX()).append(", ").append(bp.getY()).append(")");
		}
		sb.append(" - (").append(section2.getEndX()).append(", ").append(section2.getEndY()).append(")");
		
		return sb.toString();
	}
	
	private ElkNode createGraph() {
		var factory = ElkGraphFactory.eINSTANCE;
		var graph = factory.createElkNode();
		graph.setProperty(CoreOptions.ALGORITHM, "de.cau.cs.kieler.kiml.libavoid");
		
		var n1 = factory.createElkNode();
		graph.getChildren().add(n1);
		n1.setLocation(20, 20);
		n1.setDimensions(30, 30);
		n1.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		
		var p1 = factory.createElkPort();
		n1.getPorts().add(p1);
		p1.setLocation(30, 10);
		p1.setDimensions(5, 5);
		p1.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var p2 = factory.createElkPort();
		n1.getPorts().add(p2);
		p2.setLocation(30, 20);
		p2.setDimensions(5, 5);
		p2.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var n2 = factory.createElkNode();
		graph.getChildren().add(n2);
		n2.setLocation(80, 20);
		n2.setDimensions(30, 30);
		n2.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		
		var p3 = factory.createElkPort();
		n2.getPorts().add(p3);
		p3.setLocation(-5, 12);
		p3.setDimensions(5, 5);
		p3.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);
		
		var n3 = factory.createElkNode();
		graph.getChildren().add(n3);
		n3.setLocation(150, 30);
		n3.setDimensions(30, 30);
		n3.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		
		var p4 = factory.createElkPort();
		n3.getPorts().add(p4);
		p4.setLocation(-5, 12);
		p4.setDimensions(5, 5);
		p4.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);
		
		var e1 = factory.createElkEdge();
		graph.getContainedEdges().add(e1);
		e1.getSources().add(p1);
		e1.getTargets().add(p3);
		
		var e2 = factory.createElkEdge();
		graph.getContainedEdges().add(e2);
		e2.getSources().add(p2);
		e2.getTargets().add(p4);
		
		return graph;
	}
	
}
