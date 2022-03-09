import * as React from "react";
const vis = require("./../../js/vis-timeline-graph2d.min.js");


// Create a DataSet (allows two way data-binding)
var items = new vis.DataSet([
  { id: 7, group: 2, style:"background-color: pink;",content: "Exam #1", start: "2022-04-18 3:00 am" , end: "2022-04-18 5:00 am" },
  { id: 8, group: 2, content: "Quiz 2", start: "2022-04-14 8:00 am", end: "2022-04-17 5:00 pm" },
  { id: 9, group: 2, content: "Quiz 4", start: "2022-04-17 9:00 am" , end: "2022-04-17 11:30 pm" },
  { id: 10, group: 2, content: "Quiz 5", start: "2022-04-17 8:30 am" , end: "2022-04-18 11:45 pm" },
  { id: 11, group: 2, content: "Quiz 6", start: "2022-04-17 8:45 am" , end: "2022-04-19 12:00 am" },
  { id: 12, group: 2, content: "Quiz 7", start: "2022-04-18 3:15 am" , end: "2022-04-18 10:00 pm" },
  { id: 13, group: 2, content: "Quiz 8", start: "2022-04-18 3:30 am" , end: "2022-04-19 12:00 am" },
  { id: 14, group: 2, content: "Quiz 9", start: "2022-04-19 3:30 am" , end: "2022-04-21 12:00 am" },
  { id: 15, group: 2, content: "Quiz 10", start: "2022-04-19 2:30 am" , end: "2022-04-21 12:30 am" },
  { id: 3, group: 2, content: "Homework 3", start: "2022-04-17 8:00 am" , end: "2022-04-17 11:59 pm" },
  { id: 4, group: 2, style:"background-color: pink;",content: "Exam 4", start: "2022-04-16", end: "2022-04-17" },
  { id: 5, group: 2, content: "Homework 4", start: "2022-04-15", end: "2022-04-17" },
  { id: 6, group: 2, content: "Physics Quiz 1", start: "2022-04-14", end: "2022-04-16" },
  { id: 1, group: 1, content: "Release Window #1", start: "2022-04-18 12:00 am", end: "2022-04-18 02:00 am", style:"background-color: yellow;"},
  { id: 2, group: 1, content: "Release Window #2", start: "2022-04-19 12:00 am", end: "2022-04-19 02:00 am", style:"background-color: yellow;"},
]);

// Configuration for the Timeline
const options = {};
var groups = [{
  id: 1,
  content: "Release Windows"
},{
  id: 2,
  content: "Assignments"
}];


export default function ReleaseWindows(props:{labels: any[], releaseWindows: any[], usage: any[]}) {

  React.useEffect(() => {
    var container: HTMLElement = document.getElementById('timeline');
    console.log(container);

    var timeline = new vis.Timeline(container,items, groups, options);
  }, []);

  return (<><div id="timeline"></div>
      </>);
}
