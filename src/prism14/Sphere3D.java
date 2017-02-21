package prism14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Background;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.geometry.Triangulator;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Sphere3D extends GradientPanel3{


	  /**
	   * This function builds the view branch of the scene graph. It creates a
	   * branch group and then creates the necessary view elements to give a
	   * useful view of our content.
	   * 
	   * @param c
	   *            Canvas3D that will display the view
	   * @return BranchGroup that is the root of the view elements
	   */
	  protected BranchGroup buildViewBranch(Canvas3D c) {
	    BranchGroup viewBranch = new BranchGroup();
	    Transform3D viewXfm = new Transform3D();
	    viewXfm.set(new Vector3f(0.0f, 0.0f, 10.0f));
	    TransformGroup viewXfmGroup = new TransformGroup(viewXfm);
	    ViewPlatform myViewPlatform = new ViewPlatform();
	    PhysicalBody myBody = new PhysicalBody();
	    PhysicalEnvironment myEnvironment = new PhysicalEnvironment();
	    viewXfmGroup.addChild(myViewPlatform);
	    viewBranch.addChild(viewXfmGroup);
	    View myView = new View();
	    myView.addCanvas3D(c);
	    myView.attachViewPlatform(myViewPlatform);
	    myView.setPhysicalBody(myBody);
	    myView.setPhysicalEnvironment(myEnvironment);
	    return viewBranch;
	  }

	  private void addLights(BranchGroup b) {
	    // Create a bounds for the lights
	    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
	        100.0);
	    // Set up the global lights
	    Color3f lightColour1 = new Color3f(1.0f, 1.0f, 1.0f);
	    Vector3f lightDir1 = new Vector3f(-1.0f, -1.0f, -1.0f);
	    Color3f lightColour2 = new Color3f(1.0f, 1.0f, 1.0f);
	    Point3f lightPosition2 = new Point3f(3.0f, 3.0f, 3.0f);
	    Point3f lightAtten2 = new Point3f(0.0f, 0.0f, 1.0f);
	    Vector3f lightDir2 = new Vector3f(-1.0f, -1.0f, -1.0f);
	    Color3f ambientColour = new Color3f(0.2f, 0.2f, 0.2f);
	    AmbientLight ambientLight1 = new AmbientLight(ambientColour);
	    ambientLight1.setInfluencingBounds(bounds);
	    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
	    light1.setInfluencingBounds(bounds);
	    PointLight light2 = new PointLight(lightColour2, lightPosition2,
	        lightAtten2);
	    light2.setInfluencingBounds(bounds);
//	    Background bg = new Background(new Color3f(getBackground()));
//	    Background bg = new Background(new Color3f(getBackground()));
	    Background bg = new Background();
	    bg.setApplicationBounds(bounds);
	    b.addChild(ambientLight1);
	    b.addChild(light1);
	    b.addChild(bg);
//	    b.addChild(light2);
	  }

	  protected BranchGroup buildContentBranchRed() {
	    BranchGroup contentBranch = new BranchGroup();
	    Transform3D rotateCube = new Transform3D();
	    rotateCube.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
	    //                rotateCube.set(new AxisAngle4d(1.0,0.0,0.0,Math.PI/2.0));
	    TransformGroup rotationGroup = new TransformGroup(rotateCube);
	    contentBranch.addChild(rotationGroup);
	    Appearance app = new Appearance();
	    Color3f ambientColour = new Color3f(1.0f, 0.0f, 0.0f);
	    Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
	    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
	    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
	    float shininess = 20.0f;
	    app.setMaterial(new Material(ambientColour, emissiveColour,
	        diffuseColour, specularColour, shininess));
	    rotationGroup.addChild(new Sphere(2.0f, Sphere.GENERATE_NORMALS, 120,
	        app));
	    addLights(contentBranch);
	    return contentBranch;
	  }

	  protected BranchGroup buildContentBranchGreen() {
		    BranchGroup contentBranch = new BranchGroup();
		    Transform3D rotateCube = new Transform3D();
		    rotateCube.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
		    //                rotateCube.set(new AxisAngle4d(1.0,0.0,0.0,Math.PI/2.0));
		    TransformGroup rotationGroup = new TransformGroup(rotateCube);
		    contentBranch.addChild(rotationGroup);
		    Appearance app = new Appearance();
		    Color3f ambientColour = new Color3f(0.0f, 1.0f, 0.0f);
		    Color3f diffuseColour = new Color3f(0.0f, 1.0f, 0.0f);
		    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
		    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
		    float shininess = 20.0f;
		    app.setMaterial(new Material(ambientColour, emissiveColour,
		        diffuseColour, specularColour, shininess));
		    rotationGroup.addChild(new Sphere(2.0f, Sphere.GENERATE_NORMALS, 120,
		        app));
		    addLights(contentBranch);
		    return contentBranch;
		  }
	  
	  protected BranchGroup buildContentBranchYellow() {
		    BranchGroup contentBranch = new BranchGroup();
		    Transform3D rotateCube = new Transform3D();
		    rotateCube.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
		    //                rotateCube.set(new AxisAngle4d(1.0,0.0,0.0,Math.PI/2.0));
		    TransformGroup rotationGroup = new TransformGroup(rotateCube);
		    contentBranch.addChild(rotationGroup);
		    Appearance app = new Appearance();
		    Color3f ambientColour = new Color3f(1.0f, 1.0f, 0.5f);
		    Color3f diffuseColour = new Color3f(1.0f, 1.0f, 0.5f);
		    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
		    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
		    float shininess = 20.0f;
		    app.setMaterial(new Material(ambientColour, emissiveColour,
		        diffuseColour, specularColour, shininess));
		    rotationGroup.addChild(new Sphere(2.0f, Sphere.GENERATE_NORMALS, 120,
		        app));
		    addLights(contentBranch);
		    return contentBranch;
		  }

	  public Sphere3D() {
		  
		GraphicsConfiguration config =
		           SimpleUniverse.getPreferredConfiguration();
		Canvas3D myCanvas3D = new Canvas3D(config);
		Canvas3D myCanvas3D2 = new Canvas3D(config);
		Canvas3D myCanvas3D3 = new Canvas3D(config);
	    VirtualUniverse myUniverse = new VirtualUniverse();
	    VirtualUniverse myUniverse2 = new VirtualUniverse();
	    VirtualUniverse myUniverse3 = new VirtualUniverse();
	    Locale myLocale = new Locale(myUniverse);
	    myLocale.addBranchGraph(buildViewBranch(myCanvas3D));
	    myLocale.addBranchGraph(buildContentBranchRed());
	    myCanvas3D.setSize(40, 40);
	    Locale myLocale2 = new Locale(myUniverse2);
	    myLocale2.addBranchGraph(buildViewBranch(myCanvas3D2));
	    myLocale2.addBranchGraph(buildContentBranchGreen());
	    myCanvas3D2.setSize(40, 40);
	    Locale myLocale3 = new Locale(myUniverse3);
	    myLocale3.addBranchGraph(buildViewBranch(myCanvas3D3));
	    myLocale3.addBranchGraph(buildContentBranchYellow());
	    myCanvas3D3.setSize(40, 40);
	    setLayout(new FlowLayout());
	    
	    add(myCanvas3D);
	    add(myCanvas3D3);
	    add(myCanvas3D2);
	  }

	}