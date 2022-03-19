import React from "react";
import { render } from "@testing-library/react";
import ReleaseWindows from "../components/ReleaseWindows";
import DataSource from "../components/DueDateReport";

const dataSource: DataSource = {
    items: [
        { "id": 1, "group": 1, "className":"item-release-window", "content": "Release Window #1", "start": "2022-04-18", "end": "2022-04-18"},
        { "id": 2, "group": 1, "className":"item-release-window", "content": "Release Window #2", "start": "2022-04-19", "end": "2022-04-19"},
        { "id": 3, "group": 2, "content": "Homework 3", "start": "2022-04-17" , "end": "2022-04-17" },
        { "id": 4, "group": 2, "className":"item-exam", "content": "Exam 4", "start": "2022-04-16", "end": "2022-04-17" },
        { "id": 5, "group": 2, "className":"item-exam","content": "Exam #1", "start": "2022-04-18" , "end": "2022-04-18" },
      ],
    rawData: {
        columns: [],
        rows: []
    }
};


test("<ReleaseWindows", () => {
  const { getByTitle, getByText } = render(<ReleaseWindows items={dataSource.items}/>);
  var element = getByText(/Release Windows/i);
  expect(element).toBeInTheDocument();
  element = getByText(/Assignments/i);
  expect(element).toBeInTheDocument();
});