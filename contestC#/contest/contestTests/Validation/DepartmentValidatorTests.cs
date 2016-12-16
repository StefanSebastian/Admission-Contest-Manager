using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Domain;

namespace contest.Validation.Tests
{
    [TestClass()]
    public class DepartmentValidatorTests
    {
        /*
         * Validates a valid department - does not throw exception
         */
        [TestMethod()]
        public void validateValidDepartmentTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(1, "name", 100);
            try
            {
                validator.validate(department);
                Assert.IsTrue(true);
            } catch(ValidatorException exc)
            {
                Assert.IsTrue(false);
            }
        }

        /*
         * Validates name - not null 
         */
        [TestMethod()]
        public void validateInvalidNameTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(1, "", 100);
            try
            {
                validator.validate(department);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Name must not be empty.\n");
            }
        }

        /*
         * Validates id - not negative 
         */
        [TestMethod()]
        public void validateInvalidIdTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(-3, "dsds", 100);
            try
            {
                validator.validate(department);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Id must be a positive integer\n");
            }
        }

        /*
         * Validates number of places - not negative
         */
        [TestMethod()]
        public void validateInvalidNumberOfPlacesTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(2, "dsds", -100);
            try
            {
                validator.validate(department);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Number of places must be a positive integer.\n");
            }
        }

        /*
         * Validates combination of errors 
         */
        [TestMethod()]
        public void validateInvalidNumberOfPlacesNameTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(2, "", -100);
            try
            {
                validator.validate(department);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Name must not be empty.\nNumber of places must be a positive integer.\n");
            }
        }

        /*
        * Validates a null pointer
        */
        [TestMethod()]
        public void validateInvalidReferenceTest()
        {
            DepartmentValidator validator = new DepartmentValidator();
            Department department = new Department(1, "name", 100);
            department = null;
            try
            {
                validator.validate(department);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Department is null pointer");
            }
        }
    }
}