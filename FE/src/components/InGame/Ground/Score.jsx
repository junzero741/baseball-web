import styled from "styled-components";

const Score = ({homeTeam, awayTeam, userTeam}) => {
	return (
		<StyledScore>
			<Title>BASEBALL GAME ONLINE</Title>
			<PlayerIdentifier userTeam={userTeam}>Player</PlayerIdentifier>
			<Team>
				<TeamName>{awayTeam.name}</TeamName>
				<TeamScore>{homeTeam.score}</TeamScore>
				vs
				<TeamScore>{awayTeam.score}</TeamScore>
				<TeamName>{homeTeam.name}</TeamName>
			</Team>
		</StyledScore>
	);
};

const StyledScore = styled.div`
	height: 180px;
	border-bottom: 3px solid gray;
	background-color: #000;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-evenly;
	font-weight: bold;
`;
const Title = styled.div`
	font-size: 30px;
`;
const PlayerIdentifier = styled.div`
	position:absolute;
	color:red;
	top:150px;
	left: ${({userTeam}) => userTeam === "AWAY" ? "174px" : "721px"};
	font-size:16px;
`
const Team = styled.div`
	width: 90%;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
	color: gray;
	font-size: 40px;
`;
const TeamName = styled.div`
	color: #fff;
	font-size: 60px;
`;
const TeamScore = styled.div`
	color: #fff;
	font-size: 60px;
`;
export default Score;
