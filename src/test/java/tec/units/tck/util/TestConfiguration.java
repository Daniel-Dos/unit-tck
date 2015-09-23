/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.units.tck.util;

import static tec.units.ri.quantity.QuantityDimension.*;
import tec.units.ri.unit.Units;
import tec.units.tck.util.ServiceConfiguration;

import javax.measure.*;
import javax.measure.quantity.*;
import javax.measure.spi.Bootstrap;
import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import java.util.*;

/**
 * Created by Werner Keil on 21.12.2014.
 * 
 * @version 0.5.2, September 23, 2015
 */
public final class TestConfiguration implements ServiceConfiguration {

 
	@Override
    public Collection<Class<?>> getQuantityClasses() {
            return Arrays
                    .asList(new Class<?>[]{Quantity.class});
    }

    @Override
    public Collection<Class<?>> getUnitClasses() {
        try{
            return Arrays
                    .asList(new Class<?>[] { Class.forName("tec.units.ri.unit.BaseUnit")});
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Unit class not loadable");
        }
//        return Arrays
//                .asList(new Class[]{AbstractUnit.class});
    }

    @Override
    public Collection<? extends Unit<?>> getUnits4Test(){
//    	Unit<Length> m = Units.METRE;
//    	final Set<? extends Unit<?>> units = Units.getInstance().getUnits();
//    	return units;
    	SystemOfUnitsService service = Bootstrap
				.getService(SystemOfUnitsService.class);
    	SystemOfUnits sou = service.getSystemOfUnits();
    	return sou.getUnits();
    }
    
    @Override
    public Collection<UnitConverter> getUnitConverters4Test(){
        List<UnitConverter> ops = new ArrayList<>();
        // TODO add unit converters
        return ops;
    }

	@Override
	public Collection<Dimension> getBaseDimensions() {
		return Arrays
                .asList(new Dimension[] { AMOUNT_OF_SUBSTANCE, ELECTRIC_CURRENT, LENGTH, LUMINOUS_INTENSITY, 
                		MASS, TEMPERATURE, TIME });
	}

	@Override
	public Collection<Class<?>> getSupportedQuantityTypes() {
		return Arrays
                .asList(new Class<?>[] { Acceleration.class, Length.class, Mass.class  });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Unit getUnit4Type(Class quantityType) {
		return Units.getInstance().getUnit(quantityType);
	}
}