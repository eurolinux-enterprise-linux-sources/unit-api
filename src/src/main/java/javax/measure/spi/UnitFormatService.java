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

import java.util.Set;

import javax.measure.format.UnitFormat;

/**
 * This interface represent the service to obtain {@link UnitFormat} instances.
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:werner@uom.technology">Werner Keil</a>
 * @version 1.0, August 8, 2016
 * @since 1.0
 */
public interface UnitFormatService {

  /**
   * Returns the default unit format.
   *
   * It is up to implementations what to consider a suitable default. For some (locale-sensitive) implementations it may be a unit format based on
   * <code>Locale.current()</code> while others may return <code>getUnitFormat("Simple")</code> or <code>getUnitFormat("ISO")</code>
   *
   * @return the default {@link UnitFormat} implementation.
   */
  UnitFormat getUnitFormat();

  /**
   * Returns the unit format having the specified name or <code>null</code> if none.
   *
   * For example <code>getUnitFormat("UCUM")</code> to return a UCUM specific {@link UnitFormat} implementation.
   *
   * @param name
   *          the name of the format.
   * @return the corresponding unit format.
   */
  UnitFormat getUnitFormat(String name);

  /**
   * Gets a list with available format names for this format service.
   *
   * @return list of available formats, never null.
   */
  Set<String> getAvailableFormatNames();
}
