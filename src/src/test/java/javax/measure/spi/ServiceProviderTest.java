/*
 * Units of Measurement API
 * Copyright (c) 2014-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
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
package javax.measure.spi;

import org.junit.Test;

import static org.junit.Assert.*;

import javax.measure.Quantity;

/**
 * Tests for {@link ServiceProvider}.
 */
public class ServiceProviderTest {

  @Test(expected = NullPointerException.class)
  public void testSetDefault_Null() {
    ServiceProvider.setCurrent(null);
  }

  /**
   * Tests {@link ServiceProvider#current()} and {@link ServiceProvider#setCurrent(ServiceProvider)}. The getter and setter are tested in a single
   * method for avoiding issues with the order in which JUnit executes tests.
   */
  @Test
  public void testGetAndSetDefault() {
    assertEquals(0, ServiceProvider.available().size());
    try {
      ServiceProvider.current();
      fail("Expected no ServiceProvider before we set one.");
    } catch (IllegalStateException e) {
      // This is the expected exception.
    }
    TestServiceProvider testProv = new TestServiceProvider();
    assertNull("Expected no ServiceProvider before we set one.", ServiceProvider.setCurrent(testProv));
    assertSame("Setting the same ServiceProvider twice should be a no-op.", testProv, ServiceProvider.setCurrent(testProv));
    assertSame(testProv, ServiceProvider.current());
    assertArrayEquals(new ServiceProvider[] { testProv }, ServiceProvider.available().toArray());
  }

  private static final class TestServiceProvider extends ServiceProvider {

    @Override
    public SystemOfUnitsService getSystemOfUnitsService() {
      return null;
    }

    @Override
    public UnitFormatService getUnitFormatService() {
      return null;
    }

    @Override
    public <Q extends Quantity<Q>> QuantityFactory<Q> getQuantityFactory(Class<Q> quantity) {
      return null;
    }
  }
}
