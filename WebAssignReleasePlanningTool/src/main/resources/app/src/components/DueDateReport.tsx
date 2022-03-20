import * as React from "react";
import { useState } from "react";
import axios, { AxiosError } from "axios";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    DatePicker,
    Input, 
    InputType,
    Select, 
    TabsAlignment,
    TabsContainer,
    Tabs,
    Tab,
    TabPanelsContainer,
    TabPanel, 
    Flex, 
    FlexBehavior
} from 'react-magma-dom';
import moment from 'moment';

import RawData from "./RawData";
import ReleaseWindows from "./ReleaseWindows";
export interface DataSource {
    items:[],
    rawData: {
        columns: [],
        rows: []
    }
}

const dataSourceEmpty: DataSource = {
    items: [],
    rawData: {
        columns: [],
        rows: []
    }
};

export default function DueDateReportMagma() {
    const [dataSource, setDataSource] = useState(dataSourceEmpty);
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [duration, setDuration] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const options = [
        {
          label: "release #1",
          value: "25"
        },
        {
          label: "release #2",
          value: "60"
        },
        {
          label: "release #3",
          value: "45"
        }
      ];
  
  
    const handleSubmit = async () => {
        let formattedStartDate = (moment(startDate)).format('YYYY-MM-DD 00:00:00');
        let formattedEndDate = (moment(endDate)).format('YYYY-MM-DD 23:59:59');

        var url = "http://localhost:8080/duedatereport?";
        url += "startDate=" + formattedStartDate;
        url += "&endDate=" + formattedEndDate;
        url += "&duration=" + duration;
        console.log(url);

        axios.get(url)
            .then((response) => response.data)
            .then((data) => {
                console.log(data);
                setDataSource(data);
            });
    };

    function onSelectedItemChange(changes: any) {
        setDuration(changes.selectedItem.value);
    }
  
    return (<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Due Date Report</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <DatePicker labelText="Start" onChange={(date)=>{
                        setStartDate(date);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <DatePicker labelText="End" onChange={(date)=>{
                        setEndDate(date);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Input labelText="Duration" value={duration} type={InputType.number} onChange={(e)=>{
                        setDuration(e.currentTarget.value);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Select id="release" labelText="Release" items={options} onSelectedItemChange={onSelectedItemChange} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Button onClick={handleSubmit}>Generate</Button>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
            <TabsContainer activeIndex={0}>
                <Tabs aria-label="Sample Tabs">
                    <Tab>Release Windows</Tab>
                    <Tab>Raw Data</Tab>
                </Tabs>
                <TabPanelsContainer>
                    <TabPanel>
                    <ReleaseWindows items={dataSource.items}/>
                    </TabPanel>
                    <TabPanel>
                    <RawData columns={dataSource.rawData.columns} rows={dataSource.rawData.rows}/>
                    </TabPanel>
                </TabPanelsContainer>
            </TabsContainer>
            </Flex>
      </Flex>
      </>
    );
  }