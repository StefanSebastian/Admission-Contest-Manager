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
    public class CandidateRepositoryTests
    {
        private CandidateRepository repository;
        private CandidateValidator validator;

        [TestInitialize()]
        public void Initialize() {
            validator = new CandidateValidator();
            repository = new CandidateRepository(validator);
        }

        [TestCleanup()]
        public void Cleanup() {
            validator = null; repository = null;
        }

        [TestMethod()]
        public void CandidateRepositoryAddTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
        }

        [TestMethod()]
        public void CandidateRepositoryDeleteTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.delete(1);
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.delete(1);
            Assert.IsTrue(repository.getAll().Count == 0);
        }

        [TestMethod()]
        public void CandidateRepositoryUpdateTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(1, "name two", "0741223223", "zorilor 3");

            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);

            repository.update(1, c1);
            Candidate c2 = repository.getById(1);
            Assert.IsTrue(c2.Name.Equals("name two"));
        }

        [TestMethod()]
        public void CandidateRepositoryGetAllTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(2, "name two", "0741223223", "zorilor 3");
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.add(c1);
            Assert.IsTrue(repository.getAll().Count == 2);

            List<Candidate> list = repository.getAll();
            Assert.IsTrue(list.Count == 2);
        }

        [TestMethod()]
        public void CandidateRepositoryGetByIdTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(2, "name two", "0741223223", "zorilor 3");
            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.add(c1);
            Assert.IsTrue(repository.getAll().Count == 2);

            Candidate c2 = repository.getById(1);
            Assert.IsTrue(c2.Id.Equals(1));

            c2 = repository.getById(3);
            Assert.IsNull(c2);
        }

        [TestMethod()]
        public void CandidateRepositoryAddDuplicateTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(1, "name two", "0741223223", "zorilor 3");

            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            
            try
            {
                repository.add(c1);
                Assert.IsTrue(false);
            } catch(RepositoryException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void CandidateRepositoryAddInvalidTest()
        {
            Candidate c = new Candidate(1, "name 2", "0741123123", "zorilor 3");

            try
            {
                repository.add(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void CandidateRepositoryUpdateDifferentIDTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(2, "name two", "0741223223", "zorilor 3");
            repository.add(c);

            try
            {
                repository.update(1, c1);
                Assert.IsTrue(false);
            }
            catch (RepositoryException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void CandidateRepositoryUpdateInvalidIDTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(2, "name two", "0741223223", "zorilor 3");
            repository.add(c);

            try
            {
                repository.update(3, c1);
                Assert.IsTrue(false);
            }
            catch (RepositoryException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void CandidateRepositoryUpdateInvalidTest()
        {
            Candidate c = new Candidate(1, "name", "0741123123", "zorilor 3");
            Candidate c1 = new Candidate(1, "name two22", "0741223223", "zorilor 3");
            repository.add(c);

            try
            {
                repository.update(1, c1);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }
    }
}