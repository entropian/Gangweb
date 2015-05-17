/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.demo.JGraphAdapterDemo;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;




public class Visualizer
    extends JApplet
{
    //~ Static fields/initializers ---------------------------------------------

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(900, 700);

    //~ Instance fields --------------------------------------------------------
    //
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

    //~ Methods ----------------------------------------------------------------

    /**
     * Runs an instance of the visualizer
     * @param ID database ID of the person to be visualized
     * @param depth how many people deep the visualization goes from the original person 
     */
    public void runVisualizer(int ID, int depth)
    {
        Visualizer applet = new Visualizer();
        applet.init(ID, depth);

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Visualization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void init(int ID, int depth)
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableUndirectedGraph<String, DefaultEdge>(
                MyWeightedEdge.class);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);
        DatabaseHandler dbhandler = new DatabaseHandler();

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);
        
        ArrayList<Relationship> r = new ArrayList<Relationship>();
        ArrayList<Person> p;
        
        r = dbhandler.searchRelationshipsDepth(ID, depth);

        p = this.processRelationship(r, dbhandler, g);

        // position vertices nicely within JGraph component
        int x = 0;
        int y = 0;
        for(int count = 0; count < p.size(); count++){
        	if((count % 12) == 0){
        		y += 40;
        		x = 40;
        	}
        	positionVertexAt(p.get(count).firstName + " " + p.get(count).lastName, x, y);
        	x += 100;
        }

        // that's all there is to it!...
        dbhandler.Disconnect();
    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }
    
    public ArrayList<Person> processRelationship(ArrayList<Relationship> r, DatabaseHandler dbhandler, ListenableGraph g){
    	Person p1, p2;
    	ArrayList<Person> p = new ArrayList<Person>();
    	boolean r_status = true;
    	boolean p1_status = true;
    	boolean p2_status = true;
 
    	for(int i = 0; i < r.size(); i++){
    		for(int j = 0; j < i; j++){
    			if((r.get(i).id1 == r.get(j).id1) && (r.get(i).id2 == r.get(j).id2)){
    				r_status = false;
    			}
    			if((r.get(i).id2 == r.get(j).id1) && (r.get(i).id1 == r.get(j).id2)){
    				r_status = false;
    			}
    		}
    		if(r_status){
    			MyWeightedEdge edge = new MyWeightedEdge();
    			edge.setText(r.get(i).status);
    			p1 = dbhandler.Search(r.get(i).id1);
    			p2 = dbhandler.Search(r.get(i).id2);
    			for(int m = 0; m < p.size(); m++){
    				if(p1.id == p.get(m).id){
    					p1_status = false;
    				}
    				if(p2.id == p.get(m).id){
    					p2_status = false;
    				}
    			}
    			if(p1_status){
    				g.addVertex(p1.firstName + " " + p1.lastName);
    				p.add(p1);
    			}
    			if(p2_status){
    				g.addVertex(p2.firstName + " " + p2.lastName);
    				p.add(p2);
    			}
    			g.addEdge(p1.firstName + " " + p1.lastName, p2.firstName + " " + p2.lastName, edge);
    		}
    		r_status = true;
    		p1_status = true;
    		p2_status = true;
    	}
    	return p;
    }

 
}

// End JGraphAdapterDemo.java
