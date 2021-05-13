import { useEffect, useState } from "react";
import styled from "styled-components";
import delay from "../../../utils/delay/delay";
import BallCount from "./BallCount";
import getPitchResult from "../../../utils/getRandomPitchResult/getRandomPitchResult";
import getRandomPitchResult from "../../../utils/getRandomPitchResult/getRandomPitchResult";

const delayList = {
	windup: 200,
	throwing: 250,
	release: 150,
	run: 400,
};

const Field = ({ data, userTeam, fetchData }) => {
	const { inning, inningType, baseState, hitterRecords, awayTeam, homeTeam } = data;

	const isOffence = (userTeam === "AWAY") ^ (inningType === "TOP") ? "공격" : "수비";
	const pitcherTeam = inningType === "TOP" ? homeTeam.id : awayTeam.id;

	const [runnerList, setRunnerList] = useState([{ base: 0 }]);
	const [isPlaying, setPlaying] = useState(false);
	const [isFetching, setIsFetching] = useState(false);

	const [pitchingStep, setpitchingStep] = useState("release");

	const pitch = async (stepList) => {
		for (let step of stepList) await delay(() => setpitchingStep(step), delayList[step]);
	};

	const hit = async () => {
		if (hitterRecords.length === 1) return; // 공수교대
		console.log(hitterRecords[0].results.length); // 안타를 쳤다면 첫타자 results length 0이겠지?
		if (hitterRecords[0].results.length !== 0) return;
		console.log(hitterRecords[1].results);
		if (hitterRecords[1].results[hitterRecords[1].results.length - 1] !== "H") return;
		setPlaying(() => true);
		await delay(run, delayList.run);
		arrive();
		setPlaying(() => false);
	};

	const run = () => setRunnerList((list) => [...list.map((el) => ({ base: el.base++, isRunning: true })), { base: 0 }]);

	const arrive = () => setRunnerList((list) => [...list.map((el) => ({ ...el, isRunning: false }))]);

	const play = async () => {
		if (isFetching) return;
		setIsFetching(() => true);
		await Promise.all([fetchData(`https://baseball-ahpuh.herokuapp.com/games/1/pitch`, "POST", { teamId: pitcherTeam, pitchResult: getPitchResult() }), pitch(["windup", "throwing", "release"])]);
		setIsFetching(() => false);
	};

	const clearBase = () => {
		if (hitterRecords.length === 1 && hitterRecords[0].results.length === 0) setRunnerList(() => [{ base: 0 }]);
	};

	useEffect(() => {
		clearBase();
		hit();
	}, [data]);
	//prettier-ignore
	return (
		<StyledField>
			<BallCount hitterRecords={hitterRecords} />
			<CurrentInning>{inning}회{inningType==="TOP" ? "초" : "말"} {isOffence}</CurrentInning>
			<Pitcher step={pitchingStep} />
			{runnerList.map((el, i) => <Runner key={i} {...el} />)}
			{isPlaying || <Batter src="image/batter.png" />}
			{/* {isOffence === "수비" && <PitchButton onClick={play}>PITCH</PitchButton>} */}
			<PitchButton onClick={play}>PITCH</PitchButton>
		</StyledField>
	);
};

const StyledField = styled.div`
	width: 960px;
	height: 540px;
	background-image: url("image/base.png");
`;
const CurrentInning = styled.div`
	position: absolute;
	top: 200px;
	left: 780px;
	font-size: 30px;
`;
const PitchButton = styled.button`
	position: absolute;
	top: 507px;
	left: 431px;
	width: 100px;
	height: 35px;

	display: flex;
	align-items: center;
	justify-content: center;

	background-color: rgba(0, 0, 0, 0.65);
	color: #fff;
`;
const Pitcher = styled.img.attrs(({ step }) => ({
	src: `image/pitcher_${step}.png`,
}))`
	position: absolute;
	top: 376px;
	left: 450px;
	width: 60px;
`;

const baseLocation = [
	{ base: 0, top: "560px", left: "492px" },
	{ base: 1, top: "318px", left: "631px" },
	{ base: 2, top: "144px", left: "407px" },
	{ base: 3, top: "357px", left: "253px" },
];

const Runner = styled.img.attrs(({ base, isRunning }) => ({
	src: isRunning ? "image/runner_running.png" : "image/runner_waiting.png",
	style: baseLocation[base % 4],
}))`
	visibility: ${({ base, isRunning }) => (base % 4 === 0 && !isRunning ? "hidden" : "visible")};
	transform: ${({ base, isRunning }) => ((base % 4 === 0 || base % 4 === 1) && isRunning ? "scaleX(-1);" : "")};
	position: absolute;
	width: 80px;
	transition: top 590ms, left 590ms;
`;
const Batter = styled.img`
	position: absolute;
	top: 575px;
	left: 375px;
	width: 70px;
`;

export default Field;
