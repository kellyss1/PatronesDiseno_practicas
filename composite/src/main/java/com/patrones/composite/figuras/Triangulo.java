package com.patrones.composite.figuras;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class Triangulo implements FiguraGeometrica {

	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float x3;
	private float y3;
	private Color color;
	
	private float angulo = 0;
	
	private float rotx;
	private float roty;
	private float rotz;
	
	public Triangulo(
			float x1, float y1,
			float x2, float y2,
			float x3, float y3,
			Color color) {
		this.x1 = x1;
		this.y1 = y1;

		this.x2 = x2;
		this.y2 = y2;
	
		this.x3 = x3;
		this.y3 = y3;
		
		this.color = color;
		
		angulo = 0;
		
		rotx = 0;
		roty = 1;
		rotz = 0;
	}
	
	public void setRot(float x, float y, float z) {
		this.rotx = x;
		this.roty = y;
		this.rotz = z;
	}

	@Override
	public void draw() {
		GL11.glColor3f(this.color.getR(), this.color.getG(), this.color.getB());

		glPushMatrix();
		{

			glRotated(angulo, rotx, roty, rotz);

			glBegin(GL_TRIANGLES);
			{
				glVertex2f(x1, y1);
				glVertex2f(x2, y2);
				glVertex2f(x3, y3);
			}
			glEnd();

			angulo++;

			glPopMatrix();
		}

	}

}
