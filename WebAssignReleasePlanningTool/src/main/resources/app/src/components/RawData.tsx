import * as React from "react";
import {Container} from 'react-bootstrap';
import {  Button, Datagrid, DropdownDropDirection } from 'react-magma-dom';

export default function RawData(props:{columns: any[], rows: any[]}) {

    const columns2 = [
        { field: 'col1', header: 'Col 1' },
        { field: 'col2', header: 'Col 2' },
        { field: 'col3', header: 'Col 3' },
        { field: 'col4', header: 'Col 4' },
    ];

    const rows2 = [
        {
            id: 1,
            col1: '1 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 2,
            col1: '2 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 3,
            col1: '3 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 4,
            col1: '4 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 5,
            col1: '5 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 6,
            col1: '6 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 7,
            col1: '7  Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 8,
            col1: '8 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 9,
            col1: '9 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 10,
            col1: '10 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 11,
            col1: '11 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 12,
            col1: '12 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 13,
            col1: '13 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 14,
            col1: '14 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 15,
            col1: '15 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 16,
            col1: '16 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 17,
            col1: '17 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 18,
            col1: '18 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 19,
            col1: '19 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 20,
            col1: '20 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 21,
            col1: '21 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 22,
            col1: '22 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 23,
            col1: '23 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 24,
            col1: '24 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 25,
            col1: '25 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
    ];

    return (
        <Container>
            <Datagrid
                columns={columns2}
                rows={rows2}
                componentsProps={{
                    pagination: { dropdownDropDirection: DropdownDropDirection.down },
                }}
            />
            <Button> Export </Button>
        </Container>
    );
}