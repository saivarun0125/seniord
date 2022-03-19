import React from "react";
import { render } from "@testing-library/react";
import RawData from "../components/RawData";

test("<RawData", () => {
  const { getByTitle, getByText } = render(<RawData columns={[{ "field": "category", "header": "Category" }]} rows={[{"id": 0, "category": "Exam"}]} />);
  var element = getByText(/Category/i);
  expect(element).toBeInTheDocument();
  element = getByText(/Exam/i);
  expect(element).toBeInTheDocument();
});