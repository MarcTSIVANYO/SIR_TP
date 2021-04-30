import React from 'react' 

import { Card, Row, Col,Container } from 'react-bootstrap';
export default function PokemonCard({ pokemon }) {
    return (
        <div>
        <Container className="mt-12 maggin">
        <Row className="justify-content-md-center  ">
            {pokemon.map(p => (
                <Col xs={3} md={3}>
                    <Card border="info" 
                        key={p}
                        className="maggin-bottomTwo"  
                    >
                        <Card.Header>{p.name}</Card.Header>
                        <Card.Body>
                            <Card.Title>{p.id} </Card.Title>
                            <Card.Text> 
                            <a rel="noopener noreferrer" href={p.url} target="_blank">{p.url}</a>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            ))}
            </Row>
          </Container>
          </div>
    )
}
