import * as React from "react";
import {Container} from 'react-bootstrap';
import {  Button, Datagrid, DropdownDropDirection } from 'react-magma-dom';

export default function RawData(props:{columns: any[], rows: any[]}) {

    const columns2 = [
        { field: 'id', header: 'ID' },
        { field: 'category', header: 'Category' },
        { field: 'startDate', header: 'Start Date' },
        { field: 'endDate', header: 'End Date' },
        { field: 'daysAvailable', header: 'Days Available' },
        { field: 'rosterCount', header: 'Roster Count' },
    ];

    const rows2 = [
        {
            id: '1',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '2',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '3',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '4',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '5',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '6',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '7',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '8',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '9',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '10',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '11',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '12',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '13',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '14',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '15',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '16',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '17',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '18',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '19',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '20',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '21',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '22',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '23',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '24',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
        },
        {
            id: '25',
            category: 'Lorem ipsum dolor sit amet consectetur',
            startDate: 'Lorem ipsum dolor',
            endDate: 'Lorem ipsum dolor',
            daysAvailable: 'Lorem ipsum',
            rosterCount: 'Lorem ipsum',
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