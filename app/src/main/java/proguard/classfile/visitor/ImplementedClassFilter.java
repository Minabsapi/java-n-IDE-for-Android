/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2011 Eric Lafortune (eric@graphics.cornell.edu)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.classfile.visitor;

import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;

/**
 * This <code>ClassVisitor</code> delegates its visits to another given
 * <code>ClassVisitor</code>, except for classes that extend or implement
 * a given class.
 *
 * @author Eric Lafortune
 */
public class ImplementedClassFilter implements ClassVisitor
{
    private final Clazz        implementedClass;
    private final ClassVisitor classVisitor;


    /**
     * Creates a new ImplementedClassFilter.
     * @param implementedClass the class whose implementations will not be
     *                         visited.
     * @param classVisitor     the <code>ClassVisitor</code> to which visits will
     *                         be delegated.
     */
    public ImplementedClassFilter(Clazz        implementedClass,
                                  ClassVisitor classVisitor)
    {
        this.implementedClass = implementedClass;
        this.classVisitor     = classVisitor;
    }


    // Implementations for ClassVisitor.

    public void visitProgramClass(ProgramClass programClass)
    {
        if (!programClass.extendsOrImplements(implementedClass))
        {
            classVisitor.visitProgramClass(programClass);
        }
    }


    public void visitLibraryClass(LibraryClass libraryClass)
    {
        if (!libraryClass.extendsOrImplements(implementedClass))
        {
            classVisitor.visitLibraryClass(libraryClass);
        }
    }
}