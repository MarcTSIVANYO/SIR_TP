import React, { Component } from 'react';
import { Navbar, Nav } from 'react-bootstrap'; 

class Header extends Component {
  render() {
    return (
      <Navbar bg="primary" variant="dark" expand="lg" sticky="top">
        <Navbar.Brand href="/">Super Pokémon</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">  
            <Nav.Link href="pokemon">Groupe</Nav.Link>
            <Nav.Link href="pokemon-detail">Détail</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}

export default Header;