using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;
using contest.Domain;
using contest.Exceptions;

namespace contest.Repository.Tests
{
    [TestClass()]
    public class DepartmentRepositoryTests
    {
        private DepartmentRepository repository;
        private DepartmentValidator validator;

        [TestInitialize()]
        public void Initialize()
        {
            validator = new DepartmentValidator();
            repository = new DepartmentRepository(validator);
        }

        [TestCleanup()]
        public void Cleanup()
        {
            validator = null; repository = null;
        }


        [TestMethod()]
        public void DepartmentRepositoryAddTest()
        {
            Department c = new Department(1, "name", 120);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
        }

        [TestMethod()]
        public void DepartmentRepositoryDeleteTest()
        {
            Department c = new Department(1, "name", 120);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.delete(1);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.delete(1);
            Assert.IsTrue(repository.getAll().Count == 0);
        }

        [TestMethod()]
        public void DepartmentRepositoryUpdateTest()
        {
            Department c = new Department(1, "name", 132);
            Department c1 = new Department(1, "name two", 12);

            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);

            repository.update(1, c1);
            Department c2 = repository.getById(1);
            Assert.IsTrue(c2.Name.Equals("name two"));
        }


        [TestMethod()]
        public void DepartmentRepositoryGetAllTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(2, "name two", 12);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.add(c1);
            Assert.IsTrue(repository.getAll().Count == 2);

            List<Department> list = repository.getAll();
            Assert.IsTrue(list.Count == 2);
        }

        [TestMethod()]
        public void DepartmentRepositoryGetByIdTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(2, "name two", 12);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.add(c1);
            Assert.IsTrue(repository.getAll().Count == 2);

            Department c2 = repository.getById(1);
            Assert.IsTrue(c2.Id.Equals(1));

            c2 = repository.getById(3);
            Assert.IsNull(c2);
        }

        [TestMethod()]
        public void DepartmentRepositoryAddDuplicateTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(1, "name two", 12);

            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);

            try
            {
                repository.add(c1);
                Assert.IsTrue(false);
            }
            catch (RepositoryException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void DepartmentRepositoryAddInvalidTest()
        {
            Department c = new Department(1, "name 2", 121);

            try
            {
                repository.add(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void DepartmentRepositoryUpdateDifferentIDTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(2, "name two", 12);
            repository.add(c);

            try
            {
                repository.update(1, c1);
                Assert.IsTrue(false);
            }
            catch (RepositoryException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void DepartmentRepositoryUpdateInvalidIDTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(2, "name two", 12);
            repository.add(c);

            try
            {
                repository.update(3, c1);
                Assert.IsTrue(false);
            }
            catch (RepositoryException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void DepartmentRepositoryUpdateInvalidTest()
        {
            Department c = new Department(1, "name", 121);
            Department c1 = new Department(1, "name two22", 12);
            repository.add(c);

            try
            {
                repository.update(1, c1);
                Assert.IsTrue(false);
            }
            catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }
    }
}