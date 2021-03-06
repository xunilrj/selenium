/*
Copyright 2012 Selenium committers
Copyright 2012 Software Freedom Conservancy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


package org.openqa.selenium.testing.drivers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.testing.Ignore.Driver.IE;
import static org.openqa.selenium.testing.Ignore.Driver.SAFARI;

import com.google.common.collect.Sets;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.testing.Ignore;
import org.openqa.selenium.testing.Ignore.Driver;

import java.util.Set;

public class IgnoreComparitorUnitTest {

  private static final Platform CURRENT_PLATFORM = Platform.MAC;
  private static final Platform OTHER_PLATFORM = Platform.WINDOWS;

  private static final Set<Platform> CURRENT_PLATFORM_SET = Sets.newHashSet(CURRENT_PLATFORM);
  private static final Set<Platform> OTHER_PLATFORM_SET = Sets.newHashSet(OTHER_PLATFORM);

  IgnoreComparator ignoreComparator = new IgnoreComparator();
  
  @Before
  public void setupComparator() {
    ignoreComparator.setCurrentPlatform(CURRENT_PLATFORM);
  }

  @Test
  public void shouldNotIgnoreIfNothingBeingIgnored() {
    assertFalse(new IgnoreComparator().shouldIgnore(null));
  }

  @Test
  public void shouldIgnoreOnlyDriverBeingIgnored() {
    ignoreComparator.addDriver(SAFARI);
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldIgnoreDriverAll() {
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(Ignore.Driver.ALL),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldNotIgnoreOtherPlatform() {
    ignoreComparator.addDriver(SAFARI);
    assertFalse(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      OTHER_PLATFORM_SET)));
  }

  @Test
  public void shouldNotIgnoreOtherBrowser() {
    ignoreComparator.addDriver(SAFARI);
    assertFalse(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(IE),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldIgnoreEnabledNativeEventsIfIgnoringEnabled() {
    ignoreComparator.addDriver(SAFARI);
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldIgnoreDisabledNativeEventsIfIgnoringDisabled() {
    ignoreComparator.addDriver(SAFARI);
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldIgnoreEnabledNativeEventsIfIgnoringAll() {
    ignoreComparator.addDriver(SAFARI);
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      CURRENT_PLATFORM_SET)));
  }

  @Test
  public void shouldIgnoreDisabledNativeEventsIfIgnoringAll() {
    ignoreComparator.addDriver(SAFARI);
    assertTrue(ignoreComparator.shouldIgnore(ignoreForDriver(
      Sets.newHashSet(SAFARI),
      CURRENT_PLATFORM_SET)));
  }

  private Ignore ignoreForDriver(final Set<Driver> drivers,
                                 final Set<Platform> platforms) {
    final Ignore ignore = mock(Ignore.class);

    when(ignore.value()).thenReturn(drivers.toArray(new Driver[drivers.size()]));
    when(ignore.platforms()).thenReturn(platforms.toArray(new Platform[platforms.size()]));

    return ignore;
  }
}
