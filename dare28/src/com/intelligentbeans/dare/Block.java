package com.intelligentbeans.dare;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.intelligentbeans.boilerplate.PhysicalImage;

public class Block extends PhysicalImage  implements EggBreaker{
	public Block(Vector2 position, World world) {	
    	super(position,"block",world, true, false, 2);
    
     		
    }
}
