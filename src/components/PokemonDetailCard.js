import React from 'react'
import { Card, Row, Col, Container } from 'react-bootstrap';

export default function PokemonDetailCard({ pokemons }) {
    return (
        <Container className="mt-12">
            <Row className="justify-content-md-center maggin">
                {pokemons.map(pokemon => (
                    <Col xs={3} md={3}>
                        <Card border="info"
                            key={pokemon.id}
                            className="maggin-bottomTwo"
                        >
                            <Card.Header><h3> {pokemon.name}</h3></Card.Header>
                            <Card.Body>
                                <h6 className="card-subtitle mb-2 text-muted">Id: {pokemon.id}</h6>
                                <h6 className="card-subtitle mb-2 text-muted">Height: {pokemon.height}</h6>
                                <h6 className="card-subtitle mb-2 text-muted">Weight: {pokemon.weight}</h6>
                                <img src={pokemon.sprites['front_default']} />
                                <img src={pokemon.sprites['back_default']} />
                            </Card.Body>
                        </Card>
                    </Col>
                ))}

            </Row>
        </Container>
    )
}
