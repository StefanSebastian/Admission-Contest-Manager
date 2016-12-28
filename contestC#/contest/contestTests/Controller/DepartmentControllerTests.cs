using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Controller;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;
using contest.Repository;
using contest.Domain;

namespace contest.Controller.Tests
{
    [TestClass()]
    public class DepartmentControllerTests
    {
        private DepartmentValidator validator;
        private DepartmentRepository repository;
        private DepartmentController controller;

        [TestInitialize()]
        public void Initialize()
        {
            validator = new DepartmentValidator();
            repository = new DepartmentRepository(validator);
            controller = new DepartmentController(repository);
        }

        [TestCleanup()]
        public void Cleanup()
        {
            validator = null; repository = null; controller = null;
        }

        [TestMethod()]
        public void formatElementTest()
        {
            Department d = controller.formatElement("1", "name", "120");
            Assert.AreEqual(d.Id, 1); 
        }

        [TestMethod()]
        public void formatIdTest()
        {
            int id = controller.formatId("1");
            Assert.AreEqual(id, 1);
        }

        [TestMethod()]
        public void formatInvalidElementTest()
        {
            try
            {
                Department d = controller.formatElement("a1", "name", "12");
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void formatInvalidIdTest()
        {
            try
            {
                int id = controller.formatId("");
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void controllerTest()
        {
            controller.add("1", "name", "12");
            Assert.AreEqual(controller.getAll().Count, 1);
            controller.update("1", "1", "name name", "12");

            Department c = controller.getById("1");
            Assert.AreEqual(c.Name, "name name");
        }
    }
}