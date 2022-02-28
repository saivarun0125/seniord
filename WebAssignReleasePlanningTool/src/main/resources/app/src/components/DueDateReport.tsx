import * as React from "react";
import { useState } from "react";
import { Button, Container, Form, Row, Col } from "react-bootstrap";
import Dropdown from 'react-bootstrap/Dropdown';

export default function DueDateReport() {
    const [validated, setValidated] = useState(false);
  
    const handleSubmit = (event:  React.FormEvent) => {
      setValidated(true);
    };
  
    return (
    <Container>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Row className="mb-3">
            <Form.Group as={Col} md="4" controlId="startDate">
                <Form.Label>Start:</Form.Label>
                <Form.Control
                required
                type="text"
                placeholder="Start"
                defaultValue=""
                />
                <Form.Control.Feedback></Form.Control.Feedback>
            </Form.Group>
            </Row>
            <Row>
            <Form.Group as={Col} md="4" controlId="endDate">
                <Form.Label>End:</Form.Label>
                <Form.Control
                required
                type="text"
                placeholder="End"
                defaultValue=""
                />
                <Form.Control.Feedback></Form.Control.Feedback>
            </Form.Group>
            </Row>
            <Row>
            <Form.Group as={Col} md="4" controlId="duration">
                <Form.Label>Duration</Form.Label>
                <Form.Control
                required
                type="text"
                placeholder="Duration"
                defaultValue=""
                />
            </Form.Group>
            <Form.Group as={Col} md="4" controlId="duration">
                <Form.Label>Release:</Form.Label>
                <Form.Select aria-label="Select a release">
                    <option>Select a release</option>
                    <option value="1">release #1</option>
                    <option value="2">release #2</option>
                    <option value="3">release #3</option>
                </Form.Select>
            </Form.Group>
            </Row>
            <Row style={{padding: "1rem!important"}}>
                <Form.Group as={Col} md="4" controlId="Generate">
                <Button type="submit">Generate</Button>
                </Form.Group>
            </Row>
        </Form>
      </Container>
    );
  }