﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
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
    public class CandidateControllerTests
    {
        private CandidateValidator validator;
        private CandidateRepository repository;
        private CandidateController controller;

        [TestInitialize()]
        public void Initialize()
        {
            validator = new CandidateValidator();
            repository = new CandidateRepository(validator);
            controller = new CandidateController(repository);
        }

        [TestCleanup()]
        public void Cleanup()
        {
            validator = null; repository = null; controller = null;
        }

        [TestMethod()]
        public void formatElementTest()
        {
            Candidate c = controller.formatElement("1", "name", "0123123123", "abc");
            Assert.AreEqual(c.Id, 1);
        }

        [TestMethod()]
        public void formatInvalidElementTest()
        {
            try
            {
                Candidate c = controller.formatElement("a1", "name", "0123123123", "abc");
                Assert.IsTrue(false);
            } catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void formatIdTest()
        {
            int id = controller.formatId("1");
            Assert.AreEqual(id, 1);
        }

        [TestMethod()]
        public void formatInvalidIdTest()
        {
            try
            {
                int id = controller.formatId("");
                Assert.IsTrue(false);
            }
            catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void controllerTest()
        {
            controller.add("1", "name", "1231231231", "ab");
            Assert.AreEqual(controller.getAll().Count, 1);
            controller.update("1", "1", "name name", "1231231231", "ab");

            Candidate c = controller.getById("1");
            Assert.AreEqual(c.Name, "name name");
        }

        [TestMethod()]
        public void controllerFilterNameTest()
        {
            controller.add("1", "name", "1231231231", "ab");
            controller.add("2", "namuil", "1231231231", "ab");
            controller.add("3", "alex", "1231231231", "ab");
            List<Candidate> filteredList = controller.nameStartsWith("nam");
            Assert.AreEqual(2, filteredList.Count);

            filteredList = controller.nameStartsWith("alex");
            Assert.AreEqual(1, filteredList.Count);

            filteredList = controller.nameStartsWith("b");
            Assert.AreEqual(0, filteredList.Count);
        }

        [TestMethod()]
        public void controllerFilterAddressTest()
        {
            controller.add("1", "name", "1231231231", "ab");
            controller.add("2", "namuil", "1231231231", "abc");
            controller.add("3", "alex", "1231231231", "def");
            List<Candidate> filteredList = controller.addressStartsWith("ab");
            Assert.AreEqual(2, filteredList.Count);

            filteredList = controller.addressStartsWith("def");
            Assert.AreEqual(1, filteredList.Count);

            filteredList = controller.addressStartsWith("b");
            Assert.AreEqual(0, filteredList.Count);
        }
    }
}