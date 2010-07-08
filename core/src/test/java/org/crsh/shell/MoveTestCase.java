/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.crsh.shell;

/**
 * @version $Revision$
 */
public class MoveTestCase extends AbstractCommandTestCase {

  /**
   * Move a node in cwd to new
   * @throws Exception
   */
  public void testRelativeToRelative() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo bar");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('bar')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
  }


  /**
   * Move a node to an existing name
   */
  public void testMoveToExisting() {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo bar");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('bar[2]')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
  }

  /**
   * move 2 relative paths
   * @throws Exception
   */
  public void testSubRelativeToSubRelative() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.getNode('foo').addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo/bar foo/zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.getNode('foo').hasNode('zed')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.getNode('foo').hasNode('bar')"));
  }

  /**
   * Move an absolute path to another absolute path
   * @throws Exception
   */
  public void testAbsoluteToAbsolute() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.getNode('foo').addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv /foo/bar /zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('zed')"));
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.getNode('foo').hasNode('bar')"));
  }

  /**
   * Move a relative source to an absolute destination
   * @throws Exception
   */
  public void testRelativeToSubRelative() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo bar/zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.getNode('bar').hasNode('zed')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
  }

  /**
   * Move a relative source to an absolute destination
   * @throws Exception
   */
  public void testAbsoluteToRelative() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv /foo bar/zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.getNode('bar').hasNode('zed')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
  }


  /**
   * Move a relative source to an absolute destination
   * @throws Exception
   */
  public void testRelativeToAbsolute() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo /bar/zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.getNode('bar').hasNode('zed')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
  }

  /**
   * Move a relative source to an absolute destination
   * @throws Exception
   */
  public void testSubRelativeToAbsolute() throws Exception {
    assertOk("login ws");
    groovyShell.evaluate("session.rootNode.addNode('foo');");
    groovyShell.evaluate("session.rootNode.getNode('foo').addNode('bar');");
    groovyShell.evaluate("session.save();");
    assertOk("mv foo/bar /zed");
    groovyShell.evaluate("session.refresh(true);");
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('zed')"));
    assertTrue((Boolean)groovyShell.evaluate("return session.rootNode.hasNode('foo')"));
    assertFalse((Boolean)groovyShell.evaluate("return session.rootNode.getNode('foo').hasNode('bar')"));
  }
}
